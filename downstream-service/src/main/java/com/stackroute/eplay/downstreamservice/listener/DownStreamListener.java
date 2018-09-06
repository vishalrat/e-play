package com.stackroute.eplay.downstreamservice.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.stackroute.eplay.downstreamservice.domain.Movie;
import com.stackroute.eplay.downstreamservice.domain.MovieEvent;
import com.stackroute.eplay.downstreamservice.domain.RSVPEvent;
import com.stackroute.eplay.downstreamservice.domain.Theatre;
import com.stackroute.eplay.downstreamservice.domain.TicketedEvent;
import com.stackroute.eplay.downstreamservice.domain.User;
import com.stackroute.eplay.downstreamservice.repository.MovieEventRepository;
import com.stackroute.eplay.downstreamservice.repository.MovieRepository;
import com.stackroute.eplay.downstreamservice.repository.RSVPEventRepository;
import com.stackroute.eplay.downstreamservice.repository.TheatreRepository;
import com.stackroute.eplay.downstreamservice.repository.TicketedEventRepository;
import com.stackroute.eplay.downstreamservice.repository.UserRepository;
import com.stackroute.eplay.downstreamservice.stream.MovieEventStreams;
import com.stackroute.eplay.downstreamservice.stream.MovieStreams;
import com.stackroute.eplay.downstreamservice.stream.RSVPEventStreams;
import com.stackroute.eplay.downstreamservice.stream.TheatreStreams;
import com.stackroute.eplay.downstreamservice.stream.TicketedEventStreams;
import com.stackroute.eplay.downstreamservice.stream.UserStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@EnableBinding({MovieEventStreams.class, TicketedEventStreams.class, RSVPEventStreams.class, MovieStreams.class, TheatreStreams.class, UserStreams.class})
public class DownStreamListener {
	
	private MovieEventRepository movieEventRepository;
	private RSVPEventRepository rsvpEventRepository;
	private TicketedEventRepository ticketedEventRepository;
	private UserRepository userRepository;
	private TheatreRepository theatreRepository;
	private MovieRepository movieRepository; 
	
	@Autowired
	public DownStreamListener(MovieEventRepository movieEventRepository,RSVPEventRepository rsvpEventRepository, TicketedEventRepository ticketedEventRepository, TheatreRepository theatreRepository, UserRepository userRepository, MovieRepository movieRepository){
		this.movieEventRepository = movieEventRepository;
		this.rsvpEventRepository = rsvpEventRepository;
		this.ticketedEventRepository = ticketedEventRepository;
		this.theatreRepository = theatreRepository;
		this.userRepository = userRepository;
		this.movieRepository = movieRepository;
	}
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@StreamListener(MovieEventStreams.INPUT)
	public void movieEventPost(@Payload MovieEvent event) {
		movieEventRepository.save(event);
		logger.info(event.toString()+" movie");
	}
	
	@StreamListener(TicketedEventStreams.INPUT)
	public void ticketedEventPost(@Payload TicketedEvent event) {
		ticketedEventRepository.save(event);
		logger.info(event.toString()+" ticketedmovie");
	}
	
	@StreamListener(RSVPEventStreams.INPUT)
	public void rsvpEventPost(@Payload RSVPEvent event) {
		rsvpEventRepository.save(event);
		logger.info(event.toString()+" movieevent");
	}
	
	@StreamListener(UserStreams.INPUT)
	public void userPost(@Payload User user) {
		userRepository.save(user);
		logger.info(user.toString()+" user");
	}
	
	@StreamListener(TheatreStreams.INPUT)
	public void theatrePost(@Payload Theatre theatre) {
		theatreRepository.save(theatre);
		logger.info(theatre.toString()+" theatre");
	}
	
	@StreamListener(MovieStreams.INPUT)
	public void moviePost(@Payload Movie movie) {
		movieRepository.save(movie);
		logger.info(movie.toString()+" movie");
	}
	
}
