package com.stackroute.eplay.ticketservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.ticketservice.domain.MovieEvent;



@Repository
public interface MovieEventRepository extends MongoRepository<MovieEvent,Integer>{

	
//	
//	@Query("From Movie m where m.title=:Title")
//	public Iterable<Movie> getMovieByTitle(@Param("Title") String title);
}
