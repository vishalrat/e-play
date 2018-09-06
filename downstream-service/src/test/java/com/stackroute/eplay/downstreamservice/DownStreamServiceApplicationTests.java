// package com.stackroute.eplay.downstreamservice;

// import static org.junit.Assert.assertEquals;

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;

// import com.stackroute.eplay.downstreamservice.domain.Movie;
// import com.stackroute.eplay.downstreamservice.domain.MovieEvent;
// import com.stackroute.eplay.downstreamservice.domain.RSVPEvent;
// import com.stackroute.eplay.downstreamservice.domain.Theatre;
// import com.stackroute.eplay.downstreamservice.domain.TicketedEvent;
// import com.stackroute.eplay.downstreamservice.domain.User;
// import com.stackroute.eplay.downstreamservice.repository.MovieEventRepository;
// import com.stackroute.eplay.downstreamservice.repository.MovieRepository;
// import com.stackroute.eplay.downstreamservice.repository.RSVPEventRepository;
// import com.stackroute.eplay.downstreamservice.repository.TheatreRepository;
// import com.stackroute.eplay.downstreamservice.repository.TicketedEventRepository;
// import com.stackroute.eplay.downstreamservice.repository.UserRepository;

// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class DownStreamServiceApplicationTests {

// 	@Autowired
// 	private MovieEventRepository movieEventRepository;
// 	@Autowired
// 	private RSVPEventRepository rsvpEventRepository;
// 	@Autowired
// 	private TicketedEventRepository ticketedEventRepository;
// 	@Autowired
// 	private UserRepository userRepository;
// 	@Autowired
// 	private TheatreRepository theatreRepository;
// 	@Autowired
// 	private MovieRepository movieRepository;
	
// 	TicketedEvent ticketedEvent;
// 	MovieEvent movieEvent;
// 	RSVPEvent rsvpEvent;
// 	User user;
// 	Theatre theatre;
// 	Movie movie;

// 	@Before
//     public void setUp() throws Exception {
//         movie = new Movie();
//         movie.setId(1);
        
//         ticketedEvent = new TicketedEvent();
//         ticketedEvent.setId(2);
        
//         movieEvent = new MovieEvent();
//         movieEvent.setMovieEventId(3);
        
//         rsvpEvent = new RSVPEvent();
//         rsvpEvent.setId(4);
        
//         user = new User();
//         user.setUserName("5");
        
//         theatre = new Theatre();
//         theatre.setTheatreId(6);
        
//     }
	
// 	@After
//     public void tearDown() throws Exception {
// 		movieRepository.delete(movie);
// 		ticketedEventRepository.delete(ticketedEvent);
// 		movieEventRepository.delete(movieEvent);
// 		rsvpEventRepository.delete(rsvpEvent);
// 		userRepository.delete(user);
// 		theatreRepository.delete(theatre);
//     }

// 	@Test
// 	public void saveMovie() throws Exception {
// 		movieRepository.save(movie);
// 		assertEquals(movie.getId(), movieRepository.findById(1).get().getId());
// 	}
	
// 	@Test
// 	public void saveMovieEvent() throws Exception {
// 		movieEventRepository.save(movieEvent);
// 		assertEquals(movieEvent.getMovieEventId(), movieEventRepository.findById(3).get().getMovieEventId());
// 	}
	
// 	@Test
// 	public void saveTicketedEvent() throws Exception {
// 		ticketedEventRepository.save(ticketedEvent);
// 		assertEquals(ticketedEvent.getId(), ticketedEventRepository.findById(2).get().getId());
// 	}
	
// 	@Test
// 	public void saveRSVPEvent() throws Exception {
// 		rsvpEventRepository.save(rsvpEvent);
// 		assertEquals(rsvpEvent.getId(), rsvpEventRepository.findById(4).get().getId());
// 	}
	
// 	@Test
// 	public void saveUser() throws Exception {
// 		userRepository.save(user);
// 		assertEquals(user.getUserName(), userRepository.findById("5").get().getUserName());
// 	}
	
// 	@Test
// 	public void saveTheatre() throws Exception {
// 		theatreRepository.save(theatre);
// 		assertEquals(theatre.getTheatreId(), theatreRepository.findById(6).get().getTheatreId());
// 	}
// }
