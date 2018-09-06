package com.stackroute.eplay.userregistration.listener;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import com.stackroute.eplay.userregistration.domain.BookedMovieTickets;
import com.stackroute.eplay.userregistration.domain.InputEmailDetails;
import com.stackroute.eplay.userregistration.domain.MovieEvent;
import com.stackroute.eplay.userregistration.domain.RSVPEvent;
import com.stackroute.eplay.userregistration.domain.Registration;
import com.stackroute.eplay.userregistration.domain.Theatre;
import com.stackroute.eplay.userregistration.domain.Ticket;
import com.stackroute.eplay.userregistration.domain.TicketedEvent;
import com.stackroute.eplay.userregistration.repository.MovieEventRepository;
import com.stackroute.eplay.userregistration.service.RegisterUser;
import com.stackroute.eplay.userregistration.stream.BookTicketedEventStream;
import com.stackroute.eplay.userregistration.stream.EmailStream;
import com.stackroute.eplay.userregistration.stream.MovieBookedSeatsStream;
import com.stackroute.eplay.userregistration.stream.MovieEventStream;
import com.stackroute.eplay.userregistration.stream.RSVPEventStream;
import com.stackroute.eplay.userregistration.stream.TheatreStream;
import com.stackroute.eplay.userregistration.stream.TicketedEventStream;
import com.stackroute.eplay.userregistration.stream.UserRegistrationStream;


@EnableBinding({ TheatreStream.class, RSVPEventStream.class, MovieEventStream.class, TicketedEventStream.class, UserRegistrationStream.class, MovieBookedSeatsStream.class, EmailStream.class, BookTicketedEventStream.class })
public class KafkaListener {

	private RegisterUser registerUser;
	private UserRegistrationStream userRegistrationStream;
	private MovieEventRepository movieEventRepository;
	private EmailStream emailStream;
	private BookTicketedEventStream bookTicketedEventStream;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	public KafkaListener(RegisterUser registerUser, UserRegistrationStream userRegistrationStream, MovieEventRepository movieEventRepository, EmailStream emailStream,
			BookTicketedEventStream bookTicketedEventStream) {
		this.registerUser = registerUser;
		this.userRegistrationStream = userRegistrationStream;
		this.movieEventRepository = movieEventRepository;
		this.emailStream = emailStream;
		this.bookTicketedEventStream = bookTicketedEventStream;
	}

	@StreamListener(TheatreStream.INPUT)
	public void theatrePost(@Payload Theatre theatre) {
		String userName = theatre.getUserName();
		try {
			Registration user = registerUser.findByUsername(userName);
			System.out.println(user.getUserName() + " " + user.getFullName());
			List<Theatre> theatres;
			if (user.getTheatres() == null)
				theatres = new ArrayList<>();
			else
				theatres = user.getTheatres();
			theatres.add(theatre);
			user.setTheatres(theatres);

			/*
			 * updating the content in database
			 */

			registerUser.updateUser(user, userName);

			/*
			 * putting content in the message bus
			 */

			MessageChannel messageChannel = userRegistrationStream.outboundUserRegistration();
			messageChannel.send(MessageBuilder.withPayload(user)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@StreamListener(RSVPEventStream.INPUT)
	public void rsvpEventPost(@Payload RSVPEvent rsvpEvent) {
		System.out.println(rsvpEvent.toString() + " rsvp event");

		String userName = rsvpEvent.getUserName();
		try {
			Registration user = registerUser.findByUsername(userName);
			System.out.println(user.getUserName() + " " + user.getFullName());
			
			List<RSVPEvent> rsvpEvents=user.getRsvpEvents();
			if (user.getRsvpEvents() == null) {
				rsvpEvents = new ArrayList<>();
			}
			rsvpEvents.add(rsvpEvent);
			user.setRsvpEvents(rsvpEvents);

			/*
			 * updating the content in database
			 */

			registerUser.updateUser(user, userName);

			/*
			 * putting content in the message bus
			 */

			MessageChannel messageChannel = userRegistrationStream.outboundUserRegistration();
			messageChannel.send(MessageBuilder.withPayload(user)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@StreamListener(TicketedEventStream.INPUT)
	public void ticketedEventPost(@Payload TicketedEvent ticketedEvent) {
		System.out.println(ticketedEvent.toString() + " ticketed event");

		String userName = ticketedEvent.getUserName();
		try {
			Registration user = registerUser.findByUsername(userName);
			System.out.println(user.getUserName() + " " + user.getFullName());
			List<TicketedEvent> ticketedEvents=user.getTicketedEvent();
			
			if (ticketedEvents== null) {
				ticketedEvents = new ArrayList<>();
			}
			System.out.println(ticketedEvent.getDate());
			ticketedEvents.add(ticketedEvent);
			user.setTicketedEvent(ticketedEvents);
			

			/*
			 * updating the content in database
			 */

			registerUser.updateUser(user, userName);

			/*
			 * putting content in the message bus
			 */

			MessageChannel messageChannel = userRegistrationStream.outboundUserRegistration();
			messageChannel.send(MessageBuilder.withPayload(user)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@StreamListener(MovieEventStream.INPUT)
	public void movieEventPost(@Payload MovieEvent movieEvent) {
		System.out.println(movieEvent.toString() + " movie event");

		String userName = movieEvent.getUserName();
		try {
			Registration user = registerUser.findByUsername(userName);
			System.out.println(user.getUserName() + " " + user.getFullName());
			List<MovieEvent> movieEvents;
			if (user.getMovieEvent() == null)
				movieEvents = new ArrayList<>();
			else
				movieEvents = user.getMovieEvent();
			movieEvents.add(movieEvent);
			user.setMovieEvent(movieEvents);

			/*
			 * updating the content in database
			 */

			registerUser.updateUser(user, userName);
			movieEventRepository.save(movieEvent);
			/*
			 * putting content in the message bus
			 */

			MessageChannel messageChannel = userRegistrationStream.outboundUserRegistration();
			messageChannel.send(MessageBuilder.withPayload(user)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@StreamListener(MovieBookedSeatsStream.INPUT)
	public void bookedMovieEventPost(@Payload BookedMovieTickets bookedMovieTickets) {
		System.out.println(bookedMovieTickets.toString() + " booked seats");

		String userName = bookedMovieTickets.getUserName();
		try {
			Registration user = registerUser.findByUsername(userName);
			if(bookedMovieTickets.getStatus().equals("booked")) {
				List<Integer> bookedMovieId;
				int movieId = movieEventRepository.findById(bookedMovieTickets.getMovieEventId()).get().getMovieId();
				if (user.getBookedMovieId() == null)
					bookedMovieId = new ArrayList<>();
				else
					bookedMovieId = user.getBookedMovieId();
				if(!bookedMovieId.contains(movieId))
					bookedMovieId.add(movieId);
				user.setBookedMovieId(bookedMovieId);

				/*
				 * updating the content in database
				 */

				registerUser.updateUser(user, userName);

				/*
				 * putting content in the message bus
				 */

				MessageChannel messageChannel = userRegistrationStream.outboundUserRegistration();
				messageChannel.send(MessageBuilder.withPayload(user)
						.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
			}
			
			String message = "";
			if(bookedMovieTickets.getStatus().equals("booked")) {
				message = "Congrats, You have booked Seat No: ";
				for(int i:bookedMovieTickets.getSeats()) {
					message+=i+", ";
				}
				message+="for movieEventId: "+bookedMovieTickets.getMovieEventId();
			} else {
				message+="Your payment is failed for booking seats in movieEventId: "+bookedMovieTickets.getMovieEventId() + ". Please try again.";
			}
			InputEmailDetails email= new InputEmailDetails();
			email.setEmailAddress(user.getEmail());
			email.setSubject("Movie Seats Booking");
			email.setBody(message);
			MessageChannel messageChannelEmail = emailStream.outboundEmail();
			messageChannelEmail.send(MessageBuilder.withPayload(email)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@StreamListener(BookTicketedEventStream.INPUT)
	public void bookedTicketedEventPost(@Payload Ticket ticket) {
		logger.info("sending email for " + ticket.toString());
		String userName = ticket.getUserName();
		logger.info("entering try block...  " + userName);
		
		try {
			Registration user = registerUser.findByUsername(userName);	
			logger.info("got user " + user.toString());
			List<Integer> bookedTicketedEventId;
//			int movieId = movieEventRepository.findById(bookedMovieTickets.getMovieEventId()).get().getMovieId();
			//int eventId = 
			if (user.getBookedTicketedEventId() == null)
				bookedTicketedEventId = new ArrayList<>();
			else
				bookedTicketedEventId = user.getBookedTicketedEventId();
			
			if(!bookedTicketedEventId.contains(ticket.getTicketedEventId()))
				bookedTicketedEventId.add(ticket.getTicketedEventId());

			user.setBookedTicketedEventId(bookedTicketedEventId);
			/*
			 * updating the content in database
			 */

			registerUser.updateUser(user, userName);

			/*
			 * putting content in the message bus
			 */

			logger.info("sending updated user data...");
			MessageChannel messageChannel = userRegistrationStream.outboundUserRegistration();
			messageChannel.send(MessageBuilder.withPayload(user)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
			
//			String message = "";
//			if(bookedMovieTickets.getStatus().equals("booked")) {
//				message = "Congrats, You have booked Seat No: ";
//				for(int i:bookedMovieTickets.getSeats()) {
//					message+=i+", ";
//				}
//				message+="for movieEventId: "+bookedMovieTickets.getMovieEventId();
//			} else {
//				message+="Your payment is failed for booking seats in movieEventId: "+bookedMovieTickets.getMovieEventId() + ". Please try again.";
//			}
			logger.info("composing email");
			String message = "You have booked " + ticket.getNoOfSeats() + " tickets for the event " + ticket.getTicketedEventId() + ".\nHope you enjoy.";
			InputEmailDetails email= new InputEmailDetails();
			email.setEmailAddress(user.getEmail());
			email.setSubject("Event Seat Booking");
			email.setBody(message);
			MessageChannel messageChannelEmail = emailStream.outboundEmail();
			messageChannelEmail.send(MessageBuilder.withPayload(email)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
			logger.info("message sent to email service");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	
	
}