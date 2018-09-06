package com.stackroute.eplay.downstreamservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.downstreamservice.domain.MovieEvent;

@Repository
public interface MovieEventRepository extends MongoRepository<MovieEvent, Integer> {
}
