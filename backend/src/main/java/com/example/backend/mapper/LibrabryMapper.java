package com.example.backend.mapper;

import com.example.backend.dto.LibrabryDTO;
import com.example.backend.entity.LibrabryModel;
import org.springframework.stereotype.Component;

@Component
public class LibrabryMapper {

    public LibrabryModel map(LibrabryDTO librabryDTO) {
        LibrabryModel librabryModel = new LibrabryModel();
        librabryModel.setId(librabryDTO.getId());
        librabryModel.setFrasePt(librabryDTO.getFrasePT());
        librabryModel.setFraseEn(librabryDTO.getFraseEn());
        librabryModel.setCategoria(librabryDTO.getCategoria());
        return librabryModel;
    }
    public LibrabryDTO map(LibrabryModel model) {
        LibrabryDTO librabryDTO = new LibrabryDTO();
        librabryDTO.setId(model.getId());
        librabryDTO.setFrasePT(model.getFrasePt());
        librabryDTO.setFraseEn(model.getFraseEn());
        librabryDTO.setCategoria(model.getCategoria());
        return librabryDTO;
    }
}
