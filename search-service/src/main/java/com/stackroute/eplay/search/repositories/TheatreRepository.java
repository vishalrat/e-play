package com.stackroute.eplay.search.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.search.domain.Theatre;

@Repository
public interface TheatreRepository extends MongoRepository<Theatre, Integer> {
}
