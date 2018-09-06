package com.stackroute.eplay.recommendationservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.recommendationservice.domain.Movie;
import com.stackroute.eplay.recommendationservice.repositories.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	private MovieRepository movierepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movierepository) {
		super();
		this.movierepository = movierepository;
	}

	public Movie saveMovie(Movie movie) {
		return movierepository.save(movie);
	}

	public Movie findByName(String name) {
		return movierepository.findByName(name);
	}

	public Movie findById(int id) {
		return movierepository.findById(id);
	}

	@Override
	public void releasedIn(String cityName,int movieId) {
	movierepository.releasedIn(cityName, movieId);		
	}

	@Override
	public List<Movie> getMoviesByGenre(String genreName) {
		return movierepository.getMoviesByGenre(genreName);
	}
	
	@Override
	public List<Movie> getMoviesByCity(String name) {
		return movierepository.getMoviesByCity(name);
	}

	@Override
	public List<Movie> getMovieByCityGenre(String name, String genreName) {
		return movierepository.getMovieByCityGenre(name,genreName);
	}
}
