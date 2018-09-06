package com.stackroute.eplay.upstreamservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.stackroute.eplay.upstreamservice.domain.Movie;
import com.stackroute.eplay.upstreamservice.domain.MovieEvent;
import com.stackroute.eplay.upstreamservice.domain.RSVPEvent;
import com.stackroute.eplay.upstreamservice.domain.Show;
import com.stackroute.eplay.upstreamservice.domain.Theatre;
import com.stackroute.eplay.upstreamservice.domain.TicketedEvent;
import com.stackroute.eplay.upstreamservice.streams.MovieEventStreams;
import com.stackroute.eplay.upstreamservice.streams.MovieStreams;
import com.stackroute.eplay.upstreamservice.streams.RSVPEventStreams;
import com.stackroute.eplay.upstreamservice.streams.ShowStreams;
import com.stackroute.eplay.upstreamservice.streams.TheatreStreams;
import com.stackroute.eplay.upstreamservice.streams.TicketedEventStreams;

@Service
public class UpStreamServiceImpl implements UpStreamService{
	
	private final MovieEventStreams movieEventStream;
	private final RSVPEventStreams rsvpEventStream;
	private final TicketedEventStreams ticketedEventStream;
	private final TheatreStreams theatreStream;
	private final MovieStreams movieStream;
	private final ShowStreams showStream;
	@Autowired
	private NextSequenceService nextSequenceService;
	
	// Parameterized Constructor
	public UpStreamServiceImpl(MovieEventStreams movieEventStreams,RSVPEventStreams rsvpEventStreams,TicketedEventStreams ticketedEventStreams, TheatreStreams theatreStreams, MovieStreams movieStreams,ShowStreams showStreams) {
		this.movieEventStream = movieEventStreams;
		this.rsvpEventStream= rsvpEventStreams;
		this.ticketedEventStream= ticketedEventStreams;
		this.theatreStream= theatreStreams;
		this.movieStream= movieStreams;
		this.showStream= showStreams;
	}
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Function for posting Movie event to the Message bus
	@Override
	public void postMovieEvent(MovieEvent event){
		// Set MovieEventId 
		event.setMovieEventId(nextSequenceService.getNextSequence("counter"));
		
		// Set ShowId for each show in a movie event
		/*for(Show show: event.getShows()) {
            show.setShowId(nextSequenceService.getNextSequence("counter"));
            show.setMovieEventId(event.getMovieEventId());
        }*/
		
		MessageChannel messageChannel = movieEventStream.outboundEvents();
		messageChannel.send(MessageBuilder.withPayload(event)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build());
		logger.info(event.toString());
	}
	
	// Function for posting Ticketed event to the Message bus
	@Override
	public void postTicketedEvent(TicketedEvent event) {
		// Set Ticket event Id
		event.setId(nextSequenceService.getNextSequence("counter"));
		event.setLocalDate(event.getDate().toString().substring(0, 10));
		MessageChannel messageChannel = ticketedEventStream.outboundEvents();
		messageChannel.send(MessageBuilder.withPayload(event)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build());
		logger.info(event.toString());
	}
	
	// Function for posting RSVP event to the Message bus
	@Override
	public void postRSVPEvent(RSVPEvent event) {
		//Set RSVP event Id
		event.setId(nextSequenceService.getNextSequence("counter"));
		
		MessageChannel messageChannel = rsvpEventStream.outboundEvents();
		messageChannel.send(MessageBuilder.withPayload(event)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build());
		logger.info(event.toString());
	}

	// Function for posting Movie to the Message bus
	@Override
	public void postMovie(Movie event) {
		//Set Movie Id
		event.setId(nextSequenceService.getNextSequence("counter"));
		
		MessageChannel messageChannel = movieStream.outboundEvents();
		messageChannel.send(MessageBuilder.withPayload(event)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build());	
		logger.info(event.toString());
	}

	// Function for posting Theater to the Message bus
	@Override
	public void postTheatre(Theatre event) {
		// Set Theatre Id
		event.setTheatreId(nextSequenceService.getNextSequence("counter"));
		
		MessageChannel messageChannel = theatreStream.outboundEvents();
		messageChannel.send(MessageBuilder.withPayload(event)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build());
		logger.info(event.toString());
	}

	// Function for posting Show to the Message bus
	@Override
	public void postShow(Show event) {
		// Set show Id
		event.setShowId(nextSequenceService.getNextSequence("counter"));
		
		MessageChannel messageChannel = showStream.outboundEvents();
		messageChannel.send(MessageBuilder.withPayload(event)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build());
		logger.info(event.toString());
	}
	
}
