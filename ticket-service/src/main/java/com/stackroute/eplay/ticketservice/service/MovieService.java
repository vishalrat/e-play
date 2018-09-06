package com.stackroute.eplay.ticketservice.service;

import java.util.Optional;

import com.stackroute.eplay.ticketservice.domain.Movie;
import com.stackroute.eplay.ticketservice.exception.MovieAlreadyExistException;
import com.stackroute.eplay.ticketservice.exception.MovieNotFoundException;



public interface MovieService {
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistException;
	public Iterable<Movie> getAllMovie();
	public Optional<Movie> getMovieById(int id) throws MovieNotFoundException ;
	
}
