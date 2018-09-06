package com.stackroute.eplay.upstreamservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.eplay.upstreamservice.domain.Movie;
import com.stackroute.eplay.upstreamservice.domain.MovieEvent;
import com.stackroute.eplay.upstreamservice.domain.RSVPEvent;
import com.stackroute.eplay.upstreamservice.domain.Show;
import com.stackroute.eplay.upstreamservice.domain.Theatre;
import com.stackroute.eplay.upstreamservice.domain.TicketedEvent;
import com.stackroute.eplay.upstreamservice.service.UpStreamService;
import com.stackroute.eplay.upstreamservice.streams.MovieEventStreams;
import com.stackroute.eplay.upstreamservice.streams.MovieStreams;
import com.stackroute.eplay.upstreamservice.streams.RSVPEventStreams;
import com.stackroute.eplay.upstreamservice.streams.ShowStreams;
import com.stackroute.eplay.upstreamservice.streams.TheatreStreams;
import com.stackroute.eplay.upstreamservice.streams.TicketedEventStreams;

@RestController
@EnableBinding({TicketedEventStreams.class,MovieEventStreams.class,RSVPEventStreams.class,TheatreStreams.class,MovieStreams.class, ShowStreams.class})
@CrossOrigin("*")
@RequestMapping("api/v1/upstream")
public class UpStreamController {

	@Autowired
	private UpStreamService upStreamService;

   //Function for posting a new Movie event to Message Bus		
	@PostMapping("/movieEvent")
	public MovieEvent movie(@RequestBody MovieEvent event) {
		upStreamService.postMovieEvent(event);
		return event;
	}
	
	//Function for posting a new Ticketed event to Message Bus	
	@PostMapping("/ticketedEvent")
	public TicketedEvent ticket(@RequestBody TicketedEvent event) {
		upStreamService.postTicketedEvent(event);
		return event;
	}
	
	//Function for posting a new RSVP event to Message Bus	
	@PostMapping("/rsvpEvent")
	public RSVPEvent ticket(@RequestBody RSVPEvent event) {
		upStreamService.postRSVPEvent(event);
		return event;
	}
	
	//Function for posting a new Movie entry to Message Bus	
	@PostMapping("/movie")
	public Movie ticket(@RequestBody Movie event) {
		upStreamService.postMovie(event);
		return event;
	}
	
	//Function for posting a new Theatre entry to Message Bus	
	@PostMapping("/theatre")
	public Theatre ticket(@RequestBody Theatre event) {
		upStreamService.postTheatre(event);
		return event;
	}
	
	//Function for posting a new Show to Message Bus	
	@PostMapping("/show")
	public Show saveShow(@RequestBody Show event) {
		upStreamService.postShow(event);
		return event;
	}
	
}
