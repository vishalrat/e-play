package com.stackroute.eplay.downstreamservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.downstreamservice.domain.Theatre;

@Repository
public interface TheatreRepository extends MongoRepository<Theatre, Integer>{

}
