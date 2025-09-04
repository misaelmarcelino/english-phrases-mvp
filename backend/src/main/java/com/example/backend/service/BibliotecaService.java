package com.example.backend.service;

import com.example.backend.dto.BibliotecaDTO;
import com.example.backend.entity.BibliotecaModel;
import com.example.backend.mapper.BibliotecaMapper;
import com.example.backend.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private BibliotecaMapper bibliotecaMapper;

    public List<BibliotecaDTO> findAll() {
        List<BibliotecaModel> all = bibliotecaRepository.findAll();
        return all.stream().map(bibliotecaMapper::map).collect(Collectors.toList());
    }
    public BibliotecaDTO buscaporID(String id) {
        Optional<BibliotecaModel> buscaFrase = bibliotecaRepository.findById(id);
        return buscaFrase.map(bibliotecaMapper::map).orElse(null);
    }

    public BibliotecaDTO buscaExata(String frase) {
        Optional<BibliotecaModel> buscaexata = bibliotecaRepository.findByFrasePt(frase);
        return buscaexata.map(bibliotecaMapper::map).orElse(null);
    }
}
