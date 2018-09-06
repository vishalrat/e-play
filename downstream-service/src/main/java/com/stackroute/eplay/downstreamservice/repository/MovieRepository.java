package com.stackroute.eplay.downstreamservice.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.downstreamservice.domain.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Integer>{

}
