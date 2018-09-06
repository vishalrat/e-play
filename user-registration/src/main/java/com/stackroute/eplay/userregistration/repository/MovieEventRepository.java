package com.stackroute.eplay.userregistration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.userregistration.domain.MovieEvent;

@Repository
public interface MovieEventRepository extends MongoRepository<MovieEvent, Integer> {
}