package com.stackroute.eplay.ticketservice.controller;

import java.text.ParseException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.eplay.ticketservice.domain.Movie;
import com.stackroute.eplay.ticketservice.domain.MovieEvent;
import com.stackroute.eplay.ticketservice.domain.Ticket;
import com.stackroute.eplay.ticketservice.domain.TicketedEvent;
import com.stackroute.eplay.ticketservice.exception.MovieAlreadyExistException;
import com.stackroute.eplay.ticketservice.exception.MovieEventAlreadyExistException;
import com.stackroute.eplay.ticketservice.exception.MovieNotFoundException;
import com.stackroute.eplay.ticketservice.exception.TicketedEventAlreadyExistException;
import com.stackroute.eplay.ticketservice.service.MovieEventService;
import com.stackroute.eplay.ticketservice.service.MovieService;
import com.stackroute.eplay.ticketservice.service.TicketedEventService;
import com.stackroute.eplay.ticketservice.streams.BookTicketedEventStreams;
import com.stackroute.eplay.ticketservice.streams.FinalMovieEventStreams;
import com.stackroute.eplay.ticketservice.streams.MovieEventStreams;
import com.stackroute.eplay.ticketservice.streams.MovieStreams;
import com.stackroute.eplay.ticketservice.streams.TicketedEventStreams;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")


@RequestMapping("/api/v1")
@EnableBinding({ MovieEventStreams.class, TicketedEventStreams.class, MovieStreams.class,FinalMovieEventStreams.class, BookTicketedEventStreams.class })

public class TicketEventController {
	@Autowired
	Environment env;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	MovieEventService movieEventService;
	MovieService movieService;
	TicketedEventService ticketedEventService;

	@Autowired
	TicketEventController(MovieEventService movieEventService, TicketedEventService ticketedEventService,
			MovieService movieService) {
		this.movieEventService = movieEventService;
		this.ticketedEventService = ticketedEventService;
		this.movieService = movieService;
	}

	@PostMapping("/saveMovieEvent")
	public ResponseEntity<?> saveMovieEvent(@RequestBody MovieEvent movieEvent) {
		try {

			logger.info("Getting the movieEvent body to save into movieEventDB");
			movieEventService.saveMovieEvent(movieEvent);
			return new ResponseEntity<MovieEvent>(movieEvent, HttpStatus.CREATED);
		} catch (MovieEventAlreadyExistException e) {
			logger.error(e.getStackTrace().toString());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;

	}

	@PostMapping("/saveMovie")
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
		try {

			logger.info("Getting the movieEvent body to save into movieEventDB");
			movieService.saveMovie(movie);
			return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistException e) {
			logger.error(e.getStackTrace().toString());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/saveTicketedEvent")
	public ResponseEntity<?> saveTicketedEvent(@RequestBody TicketedEvent ticketedEvent) {
		try {
			logger.info("Getting the ticketEvent body to save into TicketDB");
			ticketedEventService.saveTicketedEvent(ticketedEvent);
			return new ResponseEntity<TicketedEvent>(ticketedEvent, HttpStatus.CREATED);
		} catch (TicketedEventAlreadyExistException e) {
			logger.error(e.getStackTrace().toString());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/getAllMovieEvent")
	public ResponseEntity<?> getAllMovieEvent() {
		return new ResponseEntity<Iterable<MovieEvent>>(movieEventService.getAllMovieEvent(), HttpStatus.OK);
	}
   @GetMapping("/getByID/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable int id) throws MovieNotFoundException{
    	
    	   Optional<Movie> movie=movieService.getMovieById(id);
    	   return new ResponseEntity<Optional<Movie>> (movie,HttpStatus.OK);
    	    	
    }
   

	@GetMapping("/getAllMovie")
	public ResponseEntity<?> getAllMovie() {
		return new ResponseEntity<Iterable<Movie>>(movieService.getAllMovie(), HttpStatus.OK);
	}

	@GetMapping("/getAllTicketedEvent")
	public ResponseEntity<?> getAllTicketedEvent() {
		return new ResponseEntity<Iterable<TicketedEvent>>(ticketedEventService.getAllTicketedEvent(), HttpStatus.OK);
	}

	@GetMapping("/getTicketedEventById/{id}")
	public ResponseEntity<?> getTicketedEventById(@PathVariable int id) {
		return new ResponseEntity<TicketedEvent>(ticketedEventService.getTicketedEventById(id), HttpStatus.OK);
	}

	@PutMapping("/updateTicketedEvent")
	public ResponseEntity<?> updateTicketedEvent(@RequestBody TicketedEvent ticketedEvent) {
		return new ResponseEntity<TicketedEvent>(ticketedEventService.updateTicketedEvent(ticketedEvent),
				HttpStatus.OK);
	}

	/*
	 * @PutMapping("/bookTicketedEvent/{id}") public String
	 * bookTicketedEvent(@PathVariable int id) { TicketedEvent ticketedEvent =
	 * ticketedEventService.getTicketedEventById(id);
	 * ticketedEvent.setRemainingSeats(ticketedEvent.getRemainingSeats()-1); return
	 * ""; }
	 */

	@PutMapping("/bookTicketedEvent")
	public ResponseEntity<?> bookTicketedEvent(@RequestBody Ticket ticket) {
		logger.info("inside controller: ticket =  " + ticket.toString());
		return new ResponseEntity<Ticket>(ticketedEventService.bookTicketedEvent(ticket), HttpStatus.OK);
	}

}
