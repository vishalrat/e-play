package com.stackroute.eplay.ticketengine.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.eplay.ticketengine.domain.BlockedSeats;
import com.stackroute.eplay.ticketengine.domain.InputEmailDetails;
import com.stackroute.eplay.ticketengine.domain.Show;
import com.stackroute.eplay.ticketengine.repository.ShowRepository;
import com.stackroute.eplay.ticketengine.service.BlockedSeatsService;
import com.stackroute.eplay.ticketengine.streams.BookedSeatsStream;
import com.stackroute.eplay.ticketengine.streams.EmailStream;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
@EnableBinding({ BookedSeatsStream.class, EmailStream.class })
public class TicketEngineController {

	private ShowRepository showRepository;
	private BlockedSeatsService blockedSeatsService;
	private BookedSeatsStream bookedSeatsStream;
	private EmailStream emailStream;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TicketEngineController(ShowRepository showRepository, BlockedSeatsService blockedSeatsService,
			BookedSeatsStream bookedSeatsStream, EmailStream emailStream) {
		this.showRepository = showRepository;
		this.blockedSeatsService = blockedSeatsService;
		this.bookedSeatsStream = bookedSeatsStream;
		this.emailStream = emailStream;
	}

	@MessageMapping("/send/message")
	@SendTo("/chat")
	public BlockedSeats seats(BlockedSeats seats) throws Exception {
		logger.info("Socket Seat: " + seats.toString());
		if (seats.getStatus().equals("open") || seats.getStatus().equals("booked")) {
			blockedSeatsService.delete(seats.getId());
			seatStatus(seats);
		}
		if (seats.getStatus().equals("blocked"))
			return blockedSeatsService.save(seats);
		else
			return seats;
	}

	@PostMapping("/show")
	public ResponseEntity<?> saveShow(@RequestBody Show show) {
		showRepository.save(show);
		return new ResponseEntity<Show>(show, HttpStatus.OK);
	}

	@PutMapping("/show")
	public ResponseEntity<?> updateShow(@RequestBody Show show) {
		showRepository.update(show);
		return new ResponseEntity<Show>(show, HttpStatus.OK);
	}

	@GetMapping("/show/{id}")
	public ResponseEntity<?> getShowById(@PathVariable Long id) {
		return new ResponseEntity<Show>(showRepository.find(id), HttpStatus.OK);
	}

	@GetMapping("/shows")
	public ResponseEntity<?> getAllShows() {
		return new ResponseEntity<Map<Long, Show>>(showRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/blockedSeats")
	public ResponseEntity<?> saveBlockedSeats(@RequestBody BlockedSeats blockedSeats) {
		try {
			return new ResponseEntity<BlockedSeats>(blockedSeatsService.save(blockedSeats), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@GetMapping("/getAllBlockedSeats")
	public ResponseEntity<?> getAllBlockedSeats() {
		return new ResponseEntity<Iterable<BlockedSeats>>(blockedSeatsService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/blockedSeats/{id}")
	public ResponseEntity<?> getBlockedSeats(@PathVariable String id) {
		return new ResponseEntity<BlockedSeats>(blockedSeatsService.findById(id).get(), HttpStatus.OK);
	}

	@DeleteMapping("/blockedSeats/{id}")
	public ResponseEntity<?> delBlockedSeats(@PathVariable String id) {
		blockedSeatsService.delete(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

	@DeleteMapping("/delBlockedSeats")
	public ResponseEntity<?> delAllblockedSeats() {
		blockedSeatsService.deleteAll();
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

	public void seatStatus(BlockedSeats seats) {
		Show show = showRepository.find(seats.getShowId());
		logger.info(seats.toString());
		for (int i : seats.getSeats()) {
			if (show.getSeats().get(i).equals("blocked")) {
				if (seats.getStatus().equals("booked"))
					show.getSeats().put(i, "booked");
				else
					show.getSeats().put(i, "open");
			}
		}
		showRepository.save(show);
		seats.setMovieEventId(show.getMovieEventId());
		if (seats.getUserName() != null) {
			MessageChannel messageChannel = bookedSeatsStream.outboundBookedSeats();
			messageChannel.send(MessageBuilder.withPayload(seats)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
		}
		String message = "";
		if (seats.getStatus().equals("booked")) {
			message = "Congrats, You have booked Seat No: ";
			for (int i : seats.getSeats()) {
				message += i + ", ";
			}
			message += "for movieEventId: " + show.getMovieEventId();
		} else {
			message += "Your payment is failed for booking seats in movieEventId: " + show.getMovieEventId()
					+ ". Please try again.";
		}
		if (seats.getGuestUserEmailId() != null && !seats.getGuestUserEmailId().isEmpty()) {
			InputEmailDetails email = new InputEmailDetails();
			email.setEmailAddress(seats.getGuestUserEmailId());
			email.setSubject("Movie Seats Booking");
			email.setBody(message);
			MessageChannel messageChannelEmail = emailStream.outboundEmail();
			messageChannelEmail.send(MessageBuilder.withPayload(email)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
		}
	}
	
	@PostMapping("/charge")
	public ResponseEntity<?> saveCharge(@RequestBody String token) {
		Stripe.apiKey = "sk_test_6ENKtOtZz0CNzeQb8cKk9nT7";
		logger.info(token);
		String id = token.split("&")[0].split("=")[1];
		String amount = token.split("&")[1].split("=")[1];
		Map<String, Object> params = new HashMap<>();
		params.put("amount", amount);
		params.put("currency", "INR");
		params.put("description", "Example charge");
		params.put("source", id);
		try {
			Charge charge = Charge.create(params);
			logger.info(charge.toString());
			return new ResponseEntity<String> (charge.toString(), HttpStatus.OK);
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
			return new ResponseEntity<String> ("payment failed", HttpStatus.BAD_REQUEST);
		}
	}
}
