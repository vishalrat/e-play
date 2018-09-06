package com.stackroute.eplay.ticketservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.ticketservice.domain.Movie;


@Repository
public interface MovieRepository extends MongoRepository<Movie,Integer>{

	
//	
//	@Query("From Movie m where m.title=:Title")
//	public Iterable<Movie> getMovieByTitle(@Param("Title") String title);
}