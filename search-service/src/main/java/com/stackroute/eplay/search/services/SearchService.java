package com.stackroute.eplay.search.services;

import java.util.List;

import com.stackroute.eplay.search.domain.City;
import com.stackroute.eplay.search.domain.Movie;
import com.stackroute.eplay.search.domain.Query;
import com.stackroute.eplay.search.domain.Theatre;
import com.stackroute.eplay.search.domain.TicketedEvent;
import com.stackroute.eplay.search.exceptions.MovieNotFoundException;

public interface SearchService {

	public Movie saveMovie(Movie movie);

	public Iterable<Movie> getMoviesByCity(String city);

	public Iterable<Query> getAllQueries();

	public City updateCityMovies(String cityName, Movie movie, Theatre theatre);

	public Movie getMovieById(int id);

	public List<Movie> getMovieAutoSuggestions(String searchstr);

	public Iterable<Movie> getMoviesByName(String name) throws MovieNotFoundException;
	
	public Theatre saveTheatre(Theatre theatre);
	
	public Theatre getTheatreById(int id);
	
	public Movie getMoviesByIdAndCity(String city, int movieId);

	public City saveTicketedEvent(TicketedEvent ticketedEvent);

	public Iterable<TicketedEvent> getEventsByCity(String city);

	public Iterable<TicketedEvent> getEventsByName(String name);
}