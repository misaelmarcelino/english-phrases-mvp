package com.example.backend.mapper;

import com.example.backend.dto.BibliotecaDTO;
import com.example.backend.entity.BibliotecaModel;
import org.springframework.stereotype.Component;

@Component
public class BibliotecaMapper {

    public BibliotecaModel map(BibliotecaDTO bibliotecaDTO) {
        BibliotecaModel bibliotecaModel = new BibliotecaModel();
        bibliotecaModel.setId(bibliotecaDTO.getId());
        bibliotecaModel.setFrasePt(bibliotecaDTO.getFrasePT());
        bibliotecaModel.setFraseEn(bibliotecaDTO.getFraseEn());
        bibliotecaModel.setCategoria(bibliotecaDTO.getCategoria());
        return bibliotecaModel;
    }
    public BibliotecaDTO map(BibliotecaModel model) {
        BibliotecaDTO bibliotecaDTO = new BibliotecaDTO();
        bibliotecaDTO.setId(model.getId());
        bibliotecaDTO.setFrasePT(model.getFrasePt());
        bibliotecaDTO.setFraseEn(model.getFraseEn());
        bibliotecaDTO.setCategoria(model.getCategoria());
        return bibliotecaDTO;
    }
}
