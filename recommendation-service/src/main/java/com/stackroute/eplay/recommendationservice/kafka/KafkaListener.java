package com.stackroute.eplay.recommendationservice.kafka;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

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
import com.stackroute.eplay.recommendationservice.streams.MovieEventStreams;
import com.stackroute.eplay.recommendationservice.streams.MovieStreams;
import com.stackroute.eplay.recommendationservice.streams.TicketedEventStreams;
import com.stackroute.eplay.recommendationservice.streams.UserStreams;


@EnableBinding({MovieEventStreams.class, TicketedEventStreams.class, MovieStreams.class, UserStreams.class})
public class KafkaListener {
	
	private MovieService movieService;
	private CityService cityservice;
	private TicketedEventService ticketedEventService;
	private UserService userservice;
	
	@Autowired
	KafkaListener(MovieService movieEventService,MovieService movieService,CityService cityservice,TicketedEventService ticketedEventService,UserService userservice){
		this.movieService=movieService;
		this.cityservice=cityservice;
		this.ticketedEventService=ticketedEventService;
		this.userservice=userservice;
	}

	//Movie from Kafka
	@StreamListener(MovieStreams.INPUT)
	public void moviePost(@Payload MovieKafka movieKafka) {		
		System.out.println(movieKafka.toString()+" movie");
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
		movieService.saveMovie(movie);
	}
	
	//MovieEvent from Kafka to populate released_in node
	@StreamListener(MovieEventStreams.INPUT)
	public void movieEventPost(@Payload MovieEvent movieEvent) {		
		int movieId = movieEvent.getMovieId();
		String cityName = movieEvent.getCity();
		City city = cityservice.findBycityName(cityName);
		if(city == null) {
			cityservice.saveCity(new City(cityName));
		}
		//Movie movie = movieService.findById(movieId);
		movieService.releasedIn(cityName, movieId);		
		System.out.println(movieEvent.toString()+" movie");
	}
	
	//TicketedEvent from Kafka
	@StreamListener(TicketedEventStreams.INPUT)
	public void TicketedEventPost(@Payload TicketedEventKafka ticketedEventKafka) {
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
		
		ticketedEventService.saveTicketedEvent(ticketedEvent);
		System.out.println(ticketedEventKafka.toString());
	}
	
	@StreamListener(UserStreams.INPUT)
	public void userPost(@Payload UserKafka userKafka) {
		System.out.println(userKafka);
		String userName = userKafka.getUserName();
//		String fullName = null;
//		if(userKafka.getFullName()!=null){
//		fullName = userKafka.getFullName();
//		}		
		City city = null;
		if(userKafka.getCity()!=null) {
	    city = new City(userKafka.getCity());
		}
		List<Movie> movies = new ArrayList<>();	
		List<TicketedEvent> events = new ArrayList<>();
		if(userKafka.getBookedMovieId()!=null) {
		for(int id:userKafka.getBookedMovieId()) {
			if(movieService.findById(id)!=null){
				movies.add(movieService.findById(id));
			}
			}}
		if(userKafka.getBookedTicketedEventId()!=null) {
		for(int id:userKafka.getBookedTicketedEventId()) {
			if(ticketedEventService.findById(id)!=null) {
				events.add(ticketedEventService.findById(id));
			}			
		}
	}
		User user = new User(userName,city,movies,events);
		userservice.saveUser(user);
		System.out.println(userKafka);
	}
	}
