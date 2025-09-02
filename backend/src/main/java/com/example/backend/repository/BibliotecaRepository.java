package com.example.backend.repository;

import com.example.backend.entity.BibliotecaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


public interface BibliotecaRepository extends MongoRepository<BibliotecaModel,String> {
    Optional<BibliotecaModel> findByFrasePt(String frasePt);

    @Query("{ 'frasePT': { $regex: ?0, $options: 'i' } }")
    List<BibliotecaModel> searchPtContains(String regex);
}
