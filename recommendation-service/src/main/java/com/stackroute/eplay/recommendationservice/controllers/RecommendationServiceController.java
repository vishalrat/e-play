package com.stackroute.eplay.recommendationservice.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.eplay.recommendationservice.domain.Category;
import com.stackroute.eplay.recommendationservice.domain.City;
import com.stackroute.eplay.recommendationservice.domain.Genre;
import com.stackroute.eplay.recommendationservice.domain.Movie;
import com.stackroute.eplay.recommendationservice.domain.MovieEvent;
import com.stackroute.eplay.recommendationservice.domain.MovieKafka;
import com.stackroute.eplay.recommendationservice.domain.TicketedEvent;
import com.stackroute.eplay.recommendationservice.domain.TicketedEventKafka;
import com.stackroute.eplay.recommendationservice.domain.User;
import com.stackroute.eplay.recommendationservice.domain.UserKafka;
import com.stackroute.eplay.recommendationservice.services.CityService;
import com.stackroute.eplay.recommendationservice.services.MovieService;
import com.stackroute.eplay.recommendationservice.services.TicketedEventService;
import com.stackroute.eplay.recommendationservice.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class RecommendationServiceController {
	private MovieService movieservice;
	private UserService userservice;
	private CityService cityservice;
	private TicketedEventService ticketedEventService;
		
	@Autowired
	public RecommendationServiceController(MovieService movieservice, UserService userservice,CityService cityservice,TicketedEventService ticketedEventService) {
		this.movieservice = movieservice;
		this.userservice = userservice;
		this.cityservice = cityservice;
		this.ticketedEventService = ticketedEventService;
	}
	
	/*
	 * createMovieNode() method is used to create movie node in neo4j database,
	 * taking moviekafka and mapping to movie model ,getting data from Kafka in MovieKafka 
	 * format.Rest end point for this method will be "api/v1/saveMovie"
	 * If the movie node  is successfully created then it will give the HTTP status code 201 
	 */
	@PostMapping("/saveMovie")
	public ResponseEntity<?> createMovieNode(@RequestBody MovieKafka movieKafka) {
		int id = movieKafka.getId();
		String name = movieKafka.getName();
		String language = movieKafka.getLanguage();
		String backGroundPoster = movieKafka.getBackGroundPoster();
		String cardPoster = movieKafka.getCardPoster();
		int ratings = movieKafka.getRating();
		String g = movieKafka.getGenre();
		LocalDate releaseDate = movieKafka.getReleaseDate();
		Genre genre = new Genre(g);
		Movie movie = new Movie(id,name,language,backGroundPoster,cardPoster,ratings,genre,releaseDate);
		return new ResponseEntity<Movie> (movieservice.saveMovie(movie),HttpStatus.OK);		
	}
	
	/*
	 * createTicketedEventNode() method is used to create Ticketed Event node in neo4j database,
	 * taking TicketedEventKafka and mapping to TicketedEvent model ,getting data from Kafka in 
 	 * TicketedEventKafka format.Rest end point for this method will be "api/v1/saveTicketedEvent"
	 * If the TicketedEvent node  is successfully created then it will give the HTTP status code 201 
	 */
	@PostMapping("/saveTicketedEvent")
	public ResponseEntity<?> createTicketedEventNode(@RequestBody TicketedEventKafka ticketedEventKafka) {
		int id = ticketedEventKafka.getId();
		String name = ticketedEventKafka.getName();
		Date date = ticketedEventKafka.getDate();
		City city = new City(ticketedEventKafka.getCity());
		Category category =  new Category(ticketedEventKafka.getType());
		String BackGroundPoster = null;
		String CardPoster = null;
		if(ticketedEventKafka.getBackGroundPoster()!=null) {
			BackGroundPoster = ticketedEventKafka.getBackGroundPoster();
		}
		if(ticketedEventKafka.getCardPoster()!=null) {
			CardPoster = ticketedEventKafka.getCardPoster();
		}
		TicketedEvent ticketedEvent = new TicketedEvent(id,name,date,city,category,BackGroundPoster,CardPoster);
		return new ResponseEntity<TicketedEvent> (ticketedEventService.saveTicketedEvent(ticketedEvent),HttpStatus.OK);		
	}
	
	/*
	 * createUserNode() method is used to create User node in neo4j database,
	 * taking UserKafka and mapping to User model ,getting data from Kafka in 
 	 * UserKafka format.Rest end point for this method will be "api/v1/saveUser"
	 * If the User node  is successfully created then it will give the HTTP status code 201 
	 */
	@PostMapping("/saveUser")	
	public ResponseEntity<?> createUserNode(@RequestBody UserKafka userKafka){
		String userName = userKafka.getUserName();
		//String fullName = userKafka.getFullName();
		City city = new City(userKafka.getCity());
		List<Movie> movies = new ArrayList<>();	
		List<TicketedEvent> events = new ArrayList<>();
		for(int id:userKafka.getBookedMovieId()) {
			movies.add(movieservice.findById(id));
		}
		for(int id:userKafka.getBookedTicketedEventId()) {
			events.add(ticketedEventService.findById(id));
		}
		User user = new User(userName,city,movies,events);
		return new ResponseEntity<User>(userservice.saveUser(user),HttpStatus.OK);
	}
	
	/*
	 * createCityNode() method is used to create city node showing the release cities of movies ,
	 * in neo4j database.Rest end point for this method will be "api/v1/ReleasedIn"
	 * If the city node  is successfully created then it will give the HTTP status code 201 
	 */
	@PostMapping("/ReleasedIn")
	public ResponseEntity<?> createCityNode(@RequestBody MovieEvent movieEvent){
		int movieId = movieEvent.getMovieId();
		String cityName = movieEvent.getCity();
		City city = cityservice.findBycityName(cityName);
		if(city == null) {
			cityservice.saveCity(new City(cityName));
		}
		Movie movie = movieservice.findById(movieId);
		movieservice.releasedIn(cityName, movieId);
		return new ResponseEntity<Movie> (movie,HttpStatus.OK);
	}
	
	/*
	 * getMovieByName() method is used for getting Movie by name,
	 * from neo4j database.Rest end point for this method will be "api/v1/getMovieByName"
	 * If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getMovieByName")
    public ResponseEntity <?> getMovieByName(@RequestParam String name) {
			return new ResponseEntity<Movie> (movieservice.findByName(name),HttpStatus.OK);	
	}
	
	/*
	 * getTicketedEventByName() method is used for getting Ticketed Event by name,
	 * from neo4j database.Rest end point for this method will be "api/v1/getTicketedEventByName"
	 * If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getTicketedEventByName")
    public ResponseEntity <?> getTicketedEventByName(@RequestParam String name) {
			return new ResponseEntity<TicketedEvent> (ticketedEventService.findByName(name),HttpStatus.OK);	
	}
	
	/*
	 * getMovieById() method is used for getting Movie by Id ,
	 * from neo4j database.Rest end point for this method will be "api/v1/getMovieById"
	 * If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getMovieById")
	public ResponseEntity<?> getMovieById(@RequestParam int id){
		return new ResponseEntity<Movie> (movieservice.findById(id),HttpStatus.OK);
	}
	
	/*
	 * getTicketedEventById() method is used for getting Ticketed Event by Id ,
	 * from neo4j database.Rest end point for this method will be "api/v1/getTicketedEventById"
	 * If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getTicketedEventById")
	public ResponseEntity<?> getTicketedEventById(@RequestParam int id){
		return new ResponseEntity<TicketedEvent> (ticketedEventService.findById(id),HttpStatus.OK);
	}
	
	/*
	 * getMoviesByGenre() method is used for getting Movies by Genre ,
	 * from neo4j database.Rest end point for this method will be "api/v1/getMoviesByGenre"
	 * If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getMoviesByGenre")
	public ResponseEntity<?> getMoviesByGenre(@RequestParam String genreName){
		return new ResponseEntity<List<Movie>> (movieservice.getMoviesByGenre(genreName),HttpStatus.OK);
	}
	
	/*
	 * getTicketedEventsByType() method is used for getting Ticketed Events by Category(type) ,
	 * from neo4j database.Rest end point for this method will be "api/v1/getTicketedEventsByType"
	 * If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getTicketedEventsByType")
	public ResponseEntity<?> getTicketedEventsByType(@RequestParam String categoryName){
		return new ResponseEntity<List<TicketedEvent>> (ticketedEventService.getTicketedEventsByType(categoryName),HttpStatus.OK);
	}
	
	/*
	 * getMoviesByCity() method is used for getting Movies in a particular city ,
	 * from neo4j database.Rest end point for this method will be "api/v1/getMoviesByCity"
	 * If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getMoviesByCity")
	public ResponseEntity<?> getMoviesByCity(@RequestParam String name){
		return new ResponseEntity<List<Movie>> (movieservice.getMoviesByCity(name),HttpStatus.OK);
	}
	
	/*
	 * getTicketedEventsByCity() method is used for getting Ticketed Events in a particular city ,
	 * from neo4j database.Rest end point for this method will be "api/v1/getTicketedEventsByCity"
	 * If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getTicketedEventsByCity")
	public ResponseEntity<?> getTicketedEventsByCity(@RequestParam String name){
		return new ResponseEntity<List<TicketedEvent>> (ticketedEventService.getTicketedEventsByCity(name),HttpStatus.OK);
	}
	
	/*
	 * getMovieByCityGenre() method is used for getting movies by genre in a particular city ,
	 * from neo4j database.Rest end point for this method will be "api/v1/getMovieByCityGenre"
	 * If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getMovieByCityGenre")
	public ResponseEntity<?> getMovieByCityGenre(@RequestParam String name,@RequestParam String genreName){
		return new ResponseEntity<List<Movie>> (movieservice.getMovieByCityGenre(name,genreName),HttpStatus.OK);
	}
	
	@GetMapping("/getMoviesByCityAndGenres")
	public ResponseEntity<?> getMoviesByCityAndGenres(@RequestParam String name,@RequestParam List<String> genreNames){
		List<Movie> movies = new ArrayList<>();
		if(genreNames!=null) {
			for(String genreName:genreNames) {
			movies.addAll(movieservice.getMovieByCityGenre(name,genreName));
			}
		}
		return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
	}
	/*
	 * getTicketedEventByCityType() method is used for getting Ticketed Events by Category(type) in a 
	 * particular city ,from neo4j database.Rest end point for this method will be 
	 * "api/v1/getTicketedEventByCityType".If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getTicketedEventByCityType")
	public ResponseEntity<?> getTicketedEventByCityType(@RequestParam String name,@RequestParam String categoryName){
		return new ResponseEntity<List<TicketedEvent>> (ticketedEventService.getTicketedEventByCityType(name,categoryName),HttpStatus.OK);
	}
	
	/*
	 * getGenreBasedMoviesForUser() method is used for recommending Movies based on a genre to a particular user,
	 * from neo4j database.Rest end point for this method will be "api/v1/getGenreBasedMoviesForUser"
	 * If successful,it will give the HTTP status code 201 
	 */
	@GetMapping("/getGenreBasedMoviesForUser")
	public ResponseEntity<?> getGenreBasedMoviesForUser(@RequestParam String userName,@RequestParam String cityName){
		return new ResponseEntity<List<Movie>>(userservice.getGenreBasedMoviesForUser(userName,cityName),HttpStatus.OK);	
	}
	
	/*
	 * getTypeBasedTicketedEventsForUser() method is used for recommending Ticketed Events based on 
	 * Category(type) to a particular user,from neo4j database.Rest end point for this method 
	 * will be "api/v1/getGenreBasedMoviesForUser".If successful,it will give the HTTP status code 201
	 */
	@GetMapping("/getTypeBasedTicketedEventsForUser")
	public ResponseEntity<?> getTypeBasedTicketedEventsForUser(@RequestParam String userName,@RequestParam String cityName){
		return new ResponseEntity<List<TicketedEvent>>(userservice.getTypeBasedTicketedEventsForUser(userName,cityName),HttpStatus.OK);	
	}	
	
	/*
	 * getCityOfUser() method is used for getting city of a particular user,from neo4j database.
	 * Rest end point for this method will be "api/v1/getCityOfUser".
	 * If successful,it will give the HTTP status code 201
	 */
	@GetMapping("/getCityOfUser")
	public ResponseEntity<?> getCityOfUser(@RequestParam String userName){
		return new ResponseEntity<City>(userservice.getCityOfUser(userName),HttpStatus.OK) ;
	}
	
	/*
	 * getCityByName() method is used for getting city by name,from neo4j database.
	 * Rest end point for this method will be "api/v1/getCityByName".
	 * If successful,it will give the HTTP status code 201
	 */
	@GetMapping("/getCityByName")
	public ResponseEntity<?> getCityByName(@RequestParam String name){
		return new ResponseEntity<City> (cityservice.findBycityName(name),HttpStatus.OK);
	}
	
	
}
