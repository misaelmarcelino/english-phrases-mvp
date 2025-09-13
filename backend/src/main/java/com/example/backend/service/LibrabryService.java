package com.example.backend.service;

import com.example.backend.dto.LibrabryDTO;
import com.example.backend.entity.LibrabryModel;
import com.example.backend.mapper.LibrabryMapper;
import com.example.backend.repository.LibrabryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibrabryService {

    @Autowired
    private LibrabryRepository librabryRepository;

    @Autowired
    private LibrabryMapper librabryMapper;

    public List<LibrabryDTO> findAll() {
        List<LibrabryModel> all = librabryRepository.findAll();
        return all.stream().map(librabryMapper::map).collect(Collectors.toList());
    }
    public LibrabryDTO FindbyID(String id) {
        Optional<LibrabryModel> find = librabryRepository.findById(id);
        return find.map(librabryMapper::map).orElse(null);
    }

    public LibrabryDTO searchExactly(String phrase) {
        Optional<LibrabryModel> search = librabryRepository.findByFrasePt(phrase);
        return search.map(librabryMapper::map).orElse(null);
    }

}
