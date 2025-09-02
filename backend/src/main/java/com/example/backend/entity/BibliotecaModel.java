package com.example.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tb_biblioteca")
public class BibliotecaModel {

    @Id
    private String id;
    private String frasePt;
    private String fraseEn;
    private String categoria;
}
