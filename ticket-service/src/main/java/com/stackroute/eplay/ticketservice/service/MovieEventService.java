package com.stackroute.eplay.ticketservice.service;

import java.text.ParseException;

import com.stackroute.eplay.ticketservice.domain.MovieEvent;
import com.stackroute.eplay.ticketservice.domain.Show;
import com.stackroute.eplay.ticketservice.exception.MovieEventAlreadyExistException;

public interface MovieEventService {
	public void saveMovieEvent(MovieEvent movieEvent) throws MovieEventAlreadyExistException, ParseException;
	public Iterable<MovieEvent> getAllMovieEvent();
	public void updateMovieEvent(Show show);

}
