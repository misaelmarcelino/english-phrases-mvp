package com.example.backend.controller;

import com.example.backend.dto.LibrabryDTO;
import com.example.backend.service.LibrabryService;
import com.example.backend.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/translate")
public class LibrabryController {

    @Autowired
    private LibrabryService librabryService;
    @Autowired
    private TranslateService translateService;


    @GetMapping
    public ResponseEntity<List<LibrabryDTO>>listAllLibrabry(){
        List<LibrabryDTO> librabry = librabryService.findAll();
        return ResponseEntity.ok(librabry);
    }

    @PostMapping("/pt-to-en")
    public Mono<ResponseEntity<String>> translatePtToEn(@RequestBody String sentencePt) {
        return translateService.translatePtToEn(sentencePt)
                .map(result -> result.isEmpty()
                        ? ResponseEntity.badRequest().body("Não foi possível traduzir a frase.")
                        : ResponseEntity.ok(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibrabryDTO> findById(@PathVariable String id) {
        LibrabryDTO result = librabryService.FindbyID(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<LibrabryDTO> searchExactly(@RequestParam String phrase) {
        LibrabryDTO result = librabryService.searchExactly(phrase);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
