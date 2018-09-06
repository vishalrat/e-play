//package com.stackroute.eplay.ticketservice.test.service;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.stackroute.eplay.ticketservice.domain.ArenaLayout;
//import com.stackroute.eplay.ticketservice.domain.MovieEvent;
//import com.stackroute.eplay.ticketservice.domain.Show;
//import com.stackroute.eplay.ticketservice.exception.MovieEventAlreadyExistException;
//import com.stackroute.eplay.ticketservice.repositories.MovieEventRepository;
//import com.stackroute.eplay.ticketservice.service.MovieEventServiceImpl;
//
//import static org.mockito.Matchers.anyInt;
//import static org.mockito.Matchers.anyString;
//
//
//@RunWith(SpringRunner.class)
//public class MovieEventServiceTest {
//
//	@Mock
//	private MovieEventRepository movieEventRepository;
//	private MovieEvent movieEvent1;
//	private MovieEvent movieEvent2;
//	private Show show1;
//	private Show show2;
//
//	/**
//	 * injecting mocks in MovieServiceImpl object
//	 */
//	@InjectMocks
//	private MovieEventServiceImpl movieServiceImpl;
//
//	/**
//	 * variable to hold user defined movies list
//	 */
//	private Optional<MovieEvent> optionMovie;
//
//	/**
//	 * Initializing the declarations
//	 */
//	@Before
//	public void setupMock() {
//		MockitoAnnotations.initMocks(this);
//		ArenaLayout arenaLayout=new ArenaLayout();
//		show1 =new Show(20,30,100,new Date(),new Date(),50,true,arenaLayout);
//		show2 =new Show(30,30,100,new Date(),new Date(),50,true,arenaLayout);
//		List<Show> shows=new ArrayList<Show>();
//		shows.add(show1);
//		shows.add(show2);
//		movieEvent1 = new MovieEvent(10, 20, 30, 40,shows,"bangalore","abhishek",1);
//		movieEvent2 = new MovieEvent(20, 20, 30, 40,shows,"chennai","deepak",1);
//		optionMovie = Optional.of(movieEvent1);
//
//	}
//
//	/**
//	 * testing mock creation
//	 */
//	@Test
//	public void testMockCreation() {
//		assertNotNull("jpaRepository creation fails: use @injectMocks on movieServicempl", movieEventRepository);
//	}
//
//	/**
//	 * testing the save method
//	 * @throws ParseException 
//	 * 
//	 * @throws MovieAlreadyExistsException
//	 */
//	@Test
//	public void testSaveMovieSuccess() throws MovieEventAlreadyExistException, ParseException {
//		ArrayList<MovieEvent> movieEventList = new ArrayList<MovieEvent>();
//		movieEventList.add(movieEvent1);
////		Iterable<MovieEvent> movieIterable = movieEventList;
//
//		when(movieEventRepository.findAll()).thenReturn(movieEventList);
//		when(movieEventRepository.save(movieEvent2)).thenReturn(movieEvent2);
//	//	when(nextSequenceService.getNextSequence(anyString())).thenReturn(1);
//		assertEquals(movieEvent2, movieServiceImpl.saveMovieEvent(movieEvent2));
//	}
//
//	@Test(expected = MovieEventAlreadyExistException.class)
//	public void testSaveMovieFailure() throws MovieEventAlreadyExistException, ParseException {
//		ArrayList<MovieEvent> movieEventList = new ArrayList<MovieEvent>();
//		movieEventList.add(movieEvent1);
//	//	Iterable<MovieEvent> movieIterable = movieEventList;
//		when(movieEventRepository.findAll()).thenReturn(movieEventList);
//		movieServiceImpl.saveMovieEvent(movieEvent1);
//
//	}
//
//
//}