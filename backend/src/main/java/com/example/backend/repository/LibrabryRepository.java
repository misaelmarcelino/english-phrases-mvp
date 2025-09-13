package com.example.backend.repository;

import com.example.backend.entity.LibrabryModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


public interface LibrabryRepository extends MongoRepository<LibrabryModel,String> {
    Optional<LibrabryModel> findByFrasePt(String frasePt);

    @Query("{ 'frasePT': { $regex: ?0, $options: 'i' } }")
    List<LibrabryModel> searchPtContains(String regex);
}
