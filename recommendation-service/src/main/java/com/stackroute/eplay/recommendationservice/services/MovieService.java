package com.stackroute.eplay.recommendationservice.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.stackroute.eplay.recommendationservice.domain.Movie;

public interface MovieService {
	public Movie saveMovie(Movie movie);
	public Movie findByName(String name);
	public Movie findById(int id);
    public void releasedIn(String cityName,int movieId);
    public List<Movie> getMoviesByGenre(String genreName);
    public List<Movie> getMoviesByCity(String name);
    public List<Movie> getMovieByCityGenre(String name,String genreName);
    
}
