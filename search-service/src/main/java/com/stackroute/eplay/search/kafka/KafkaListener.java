package com.stackroute.eplay.search.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.stackroute.eplay.search.domain.Movie;
import com.stackroute.eplay.search.domain.MovieEvent;
import com.stackroute.eplay.search.domain.Theatre;
import com.stackroute.eplay.search.domain.TicketedEvent;
import com.stackroute.eplay.search.services.SearchService;
import com.stackroute.eplay.search.streams.FinalMovieEventStream;
import com.stackroute.eplay.search.streams.MovieEventStream;
import com.stackroute.eplay.search.streams.MovieStream;
import com.stackroute.eplay.search.streams.TheatreStream;
import com.stackroute.eplay.search.streams.TicketedEventStream;

@EnableBinding({ MovieEventStream.class, MovieStream.class, TheatreStream.class , TicketedEventStream.class, FinalMovieEventStream.class})
public class KafkaListener {

	private SearchService searchService;
	// private MovieEventStream movieEventStream;
	// private MovieStream movieStream;

	@Autowired
	public KafkaListener(SearchService searchService) {
		this.searchService = searchService;
		// this.movieEventStream = movieEventStream;
		// this.movieStream = movieStream;
	}

	// Listener for movie event object stream
	@StreamListener(MovieEventStream.INPUT)
	public void movieEventPost(@Payload MovieEvent movieEvent) {
		String city = movieEvent.getCity();
		int movieId = movieEvent.getMovieId();
		Movie movie = searchService.getMovieById(movieId);
		Theatre theatre = searchService.getTheatreById(movieEvent.getTheatreId());
		theatre.setShows(movieEvent.getShows());

		searchService.updateCityMovies(city, movie, theatre);
	}
	
	@StreamListener(FinalMovieEventStream.INPUT)
	public void finalMovieEventPost(@Payload MovieEvent movieEvent) {
		String city = movieEvent.getCity();
		int movieId = movieEvent.getMovieId();
		Movie movie = searchService.getMovieById(movieId);
		Theatre theatre = searchService.getTheatreById(movieEvent.getTheatreId());
		theatre.setShows(movieEvent.getShows());

		searchService.updateCityMovies(city, movie, theatre);
	}

	// Listener for movie object stream
	@StreamListener(MovieStream.INPUT)
	public void moviePost(@Payload Movie movie) {
		searchService.saveMovie(movie);

	}

	// Listener for theatre object stream
	@StreamListener(TheatreStream.INPUT)
	public void theatrePost(@Payload Theatre theatre) {
		searchService.saveTheatre(theatre);

	}


	//Listener for ticketed event stream
	@StreamListener(TicketedEventStream.INPUT)
	public void ticketedEventPost(@Payload TicketedEvent ticketedEvent) {
		searchService.saveTicketedEvent(ticketedEvent);
	}
}
