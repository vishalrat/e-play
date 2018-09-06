package com.stackroute.eplay.ticketservice.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.ticketservice.domain.Movie;
import com.stackroute.eplay.ticketservice.exception.MovieAlreadyExistException;
import com.stackroute.eplay.ticketservice.exception.MovieNotFoundException;
import com.stackroute.eplay.ticketservice.repositories.MovieRepository;
import com.stackroute.eplay.ticketservice.streams.MovieStreams;

@Service
public class MovieServiceImpl implements MovieService {
	MovieRepository movieRepository;
	MovieStreams movieStreams;
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository,MovieStreams movieStreams) {
		this.movieRepository = movieRepository;
		this.movieStreams= movieStreams;
	}
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistException {
		Iterable<Movie> movies = getAllMovie();
		Iterator<Movie> iterator = movies.iterator();
		
		while (iterator.hasNext()) {
			Movie m = iterator.next();
			if (movie.equals(m)) {
				throw new MovieAlreadyExistException("Movie Event already exists");
			}
		}
		
		return movieRepository.save(movie);
	}
	public Iterable<Movie> getAllMovie() {

		return movieRepository.findAll();
	}
	public Optional<Movie> getMovieById(int id) throws MovieNotFoundException {
		Optional<Movie> movie = movieRepository.findById(id);
		if (movie.isPresent()) {
			return movie;
		} else {
			throw new MovieNotFoundException("Movie not found");
		}

	}

}
