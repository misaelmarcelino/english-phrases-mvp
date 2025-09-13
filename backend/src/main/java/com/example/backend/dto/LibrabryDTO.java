package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibrabryDTO {

    private String id;
    private String frasePT;
    private String fraseEn;
    private String categoria;
}
