package com.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TranslateService {

    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    private static final int MAX_INPUT_CHARS = 2000;

    public Mono<String> translatePtToEn(String sentencePt) {
        String input = sanitize(sentencePt);
        if (input.isEmpty()) return Mono.just("");
        if (input.length() > MAX_INPUT_CHARS) input = input.substring(0, MAX_INPUT_CHARS);

        String system = """
            Você é um assistente especializado em inglês americano. Sua tarefa é receber uma frase em português e devolver apenas a forma como um nativo americano falaria essa frase em 3 estilos diferentes:
            1. *Formal*
            2. *Educada / Comum*
            3. *Informal / Direta*
            Para cada estilo, mostre:
            - A frase em inglês (como um nativo falaria).
            - A tradução natural em português.
            ⚠ Importante:
            - Não explique gramática.
            - Não mostre nada além das frases e traduções.
            - O inglês deve refletir a forma real que um americano usaria no contexto.
            ### Exemplo de resposta esperada:
            *Formal:* "I'd like a hamburger without pickles, please." ("Eu gostaria de um hambúrguer sem picles, por favor.")
            *Educada:* "Can I get a hamburger with no pickles, please?" ("Posso pedir um hambúrguer sem picles, por favor?")
            *Informal:* "I'll take a burger, no pickles." ("Vou querer um hambúrguer, sem picles.")
            """;

        String user = "A frase é: " + input;

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "temperature", 0,
                "messages", List.of(
                        Map.of("role", "system", "content", system),
                        Map.of("role", "user", "content", user)
                )
        );

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofSeconds(15))
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return postProcess(message.get("content").toString());
                    }
                    return "";
                })
                .onErrorReturn("");
    }

    private String sanitize(String context) {
        if (context == null) return "";
        String switchfor = context.replaceAll("\\p{C}", "").trim();
        switchfor = switchfor.replaceAll("[ \\t\\x0B\\f\\r]+", " ");
        switchfor = switchfor.replaceAll("\\n{3,}", "\n\n");
        return switchfor;
    }

    private String postProcess(String content) {
        if (content == null) return "";
        String rep = content.replaceAll("^`{3,}\\s*|\\s*`{3,}$", "").trim();
        rep = rep.replaceAll("[ \\t]+\\n", "\n").trim();
        return rep;
    }
}
