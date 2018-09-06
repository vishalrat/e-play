//package com.stackroute.eplay.searchservice.servicestest;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.stackroute.eplay.search.domain.City;
//import com.stackroute.eplay.search.domain.Movie;
//import com.stackroute.eplay.search.repositories.CityRepository;
//import com.stackroute.eplay.search.repositories.MovieRepository;
//import com.stackroute.eplay.search.repositories.QueryRepository;
//import com.stackroute.eplay.search.services.SearchServiceImpl;
//
//@RunWith(SpringRunner.class)
//public class ServiceTests {
//
//	
//	@MockBean
//	private transient QueryRepository queryRepository;
//	
//	@MockBean
//	private transient CityRepository cityRepository;
//	
//	@MockBean
//	private transient MovieRepository movieRepository;
//	
//	@InjectMocks
//	private transient SearchServiceImpl searchServiceImpl;
//	
//	
//	private transient Movie movie1, movie2, movie3;
//	@SuppressWarnings("unused")
//	private transient City city1, city2;
//	
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		
//		movie1 = new Movie(1, "1", "1", null, null, null, null, null, 0, 0, null);
//		movie2 = new Movie(2, "2", "2", null, null, null, null, null, 0, 0, null);
//		movie3 = new Movie(3, "3", "3", null, null, null, null, null, 0, 0, null);
//		
//		List<Movie> movieList1 = new ArrayList<Movie>();
//		movieList1.add(movie1);
//		
//		List<Movie> movieList2 = new ArrayList<Movie>();
//		movieList2.add(movie2); movieList2.add(movie3);
//		
//		city1 = new City("mumbai",  movieList1);
//		city2 = new City("delhi", movieList2);
//	}
//	
//	@After
//	public void tearDown() throws Exception {
//		
//	}
//	
//	@Test
//	public void testSaveMovie() throws Exception {
//		when(searchServiceImpl.saveMovie(movie1)).thenReturn(movie1);
//		assertEquals(movie1, searchServiceImpl.saveMovie(movie1));
//	}
//	
//	@Test
//	public void testGetMovieById() throws Exception {
//		//Optional<Movie> movieOp = Optional.of(movie1);
//		when(searchServiceImpl.getMovieById(1)).thenReturn(movie1);
//		assertEquals(movie1, searchServiceImpl.getMovieById(1));
//	}
//	
//	
//	@Test
//	public void testUpdateCityMovies() throws Exception {
//		when(searchServiceImpl.updateCityMovies("mumbai",  movie1)).thenReturn(city1);
//		assertEquals(city1, searchServiceImpl.updateCityMovies("mumbai", movie1));
//	}
//	
////	@Test
////	public void testGetEventsByCity() throws Exception {
////		
////		//when(searchServiceImpl.updateCityMovies("mumbai", movie1)).thenReturn(city1);
////		List<Movie> movieList = new ArrayList<Movie>();
////		movieList.add(movie1);
////		when(cityRepository.save(new City("mumbai", movieList))).thenReturn(city1);
////		
////		assertEquals(movie1, searchServiceImpl.getEventsByCity("mumbai").);
////		
////		when(searchServiceImpl.getEventsByCity("mumbai")).thenReturn(city1.getMovieList());
////		Iterable<Movie> allMovies = searchServiceImpl.getEventsByCity("mumbai");
////		
////		assertEquals(city1.getMovieList(), allMovies);
////	}
//
//}
//
//
