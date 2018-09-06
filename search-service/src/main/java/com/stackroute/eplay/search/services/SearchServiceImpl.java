package com.stackroute.eplay.search.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.search.domain.City;
import com.stackroute.eplay.search.domain.Movie;
import com.stackroute.eplay.search.domain.Query;
import com.stackroute.eplay.search.domain.Theatre;
import com.stackroute.eplay.search.domain.TicketedEvent;
import com.stackroute.eplay.search.exceptions.MovieNotFoundException;
import com.stackroute.eplay.search.repositories.CityRepository;
import com.stackroute.eplay.search.repositories.MovieRepository;
import com.stackroute.eplay.search.repositories.QueryRepository;
import com.stackroute.eplay.search.repositories.TheatreRepository;
import com.stackroute.eplay.search.repositories.TicketedEventRepository;

@Service
public class SearchServiceImpl implements SearchService {

	private QueryRepository queryRepository;
	private CityRepository cityRepository;
	private MovieRepository movieRepository;
	private TheatreRepository theatreRepository;
	private TicketedEventRepository ticketedEventRepository;

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public SearchServiceImpl(QueryRepository queryRepository, CityRepository cityRepository,
			MovieRepository movieRepository, TicketedEventRepository ticketedEventRepository, TheatreRepository theatreRepository) {
		super();
		this.queryRepository = queryRepository;
		this.cityRepository = cityRepository;
		this.movieRepository = movieRepository;
		this.theatreRepository = theatreRepository;
		this.ticketedEventRepository = ticketedEventRepository;
	}

	// save movie to movie repository
	@Override
	public Movie saveMovie(Movie movie) {
		if (!movieRepository.existsById(movie.getId())) {
			return movieRepository.save(movie);
		}
		return null;
	}

	@Override
	public Theatre saveTheatre(Theatre theatre) {
		if (!theatreRepository.existsById(theatre.getTheatreId())) {
			return theatreRepository.save(theatre);
		}
		return null;
	}

	@Override
	public City saveTicketedEvent(TicketedEvent ticketedEvent) {
		City city;
		List<TicketedEvent> ticketedEventsList;
		if (!cityRepository.existsById(ticketedEvent.getCity())) {
			 ticketedEventsList = new ArrayList<TicketedEvent>();
			ticketedEventsList.add(ticketedEvent);

			city = new City(ticketedEvent.getCity(), null, ticketedEventsList);
			return cityRepository.save(city);
		}
        
		city = cityRepository.findById(ticketedEvent.getCity()).get();
		ticketedEventsList = city.getTicketedEventsList();
        if(ticketedEventsList==null) {
			ticketedEventsList = new ArrayList<TicketedEvent>();

        }
		ticketedEventsList.add(ticketedEvent);

		city.setTicketedEventsList(ticketedEventsList);

		ticketedEventRepository.save(ticketedEvent);
		return cityRepository.save(city);
	}

	// update list of movies of given city
	@Override
	public City updateCityMovies(String cityName, Movie movie, Theatre theatre) {
		City city;

		if (!cityRepository.existsById(cityName)) {
			List<Movie> movieList = new ArrayList<Movie>();
			List<Theatre> theatres = new ArrayList<Theatre>();
			theatres.add(theatre);
			movie.setTheatres(theatres);
			movieList.add(movie);

			city = new City(cityName, movieList, null);
			return cityRepository.save(city);
		}

		city = cityRepository.findById(cityName).get();

		Movie currMovie = null;
		
		if(city.getMovieList() == null) {
			List<Movie> movieList = new ArrayList<Movie>();
			List<Theatre> theatres = new ArrayList<Theatre>();
			theatres.add(theatre);
			movie.setTheatres(theatres);
			movieList.add(movie);
			city.setMovieList(movieList);
			return cityRepository.save(city);
		}
		
		for (Movie tempMovie : city.getMovieList()) {
			if (tempMovie.getId() == movie.getId()) {
				currMovie = tempMovie;
				break;
			}
		}
		List<Theatre> theatres;
		List<Movie> movieList = city.getMovieList();
		if (currMovie != null) {
			if (currMovie.getTheatres() == null)
				theatres = new ArrayList<Theatre>();
			else {
				theatres = currMovie.getTheatres();
				for (Theatre tempTheatre : theatres) {
					if (tempTheatre.getTheatreId() == theatre.getTheatreId()) {
						theatres.remove(tempTheatre);
						break;
					}
				}
			}
			theatres.add(theatre);
			movieList.remove(currMovie);
			currMovie.setTheatres(theatres);
		} else {
			theatres = new ArrayList<Theatre>();
			theatres.add(theatre);
			currMovie = movie;
			currMovie.setTheatres(theatres);
		}

		movieList.add(currMovie);
		city.setMovieList(movieList);
		return cityRepository.save(city);
	}

	// get movie by movie id
	@Override
	public Movie getMovieById(int id) {
		return movieRepository.findById(id);
	}

	// get theatre by theatre id
	@Override
	public Theatre getTheatreById(int id) {
		return theatreRepository.findById(id).get();
	}

	// get all movies of given city
	@Override
	public Iterable<Movie> getMoviesByCity(String city) {
		// TODO Auto-generated method stub
		Query query = new Query();
		LocalDateTime now = LocalDateTime.now();
		query.setQuery("city=" + city);
		query.setUserId("guest");
		query.setTimeStamp(now);
		queryRepository.save(query);

		// System.out.println(cityRepository.findById(city).get());
		return cityRepository.findById(city).get().getMovieList();
	}

	// get all ticketed events of given city
	@Override
	public Iterable<TicketedEvent> getEventsByCity(String city) {

		return cityRepository.findById(city).get().getTicketedEventsList();
	}

	// get all queries
	@Override
	public Iterable<Query> getAllQueries() {
		// TODO Auto-generated method stub
		return queryRepository.findAll();
	}

	// get auto-complete suggestions for search bar
	@Override
	public List<Movie> getMovieAutoSuggestions(String searchstr) {
		ArrayList<Movie> suggestions = new ArrayList<>();

		List<Movie> movieList = movieRepository.findAll();

		for (Movie movie : movieList) {

			if (movie.getName() != null && movie.getName().toLowerCase().contains(searchstr.toLowerCase())) {
				suggestions.add(movie);
			}
		}

		// truncate the list to the first n, max 10 elements
		int n = suggestions.size() > 9 ? 9 : suggestions.size();
		movieList = new ArrayList<>(suggestions.subList(0, n));

		return movieList;
	}

	// Get all movie by id from movie list
	@Override
	public Movie getMoviesByIdAndCity(String city, int movieId) {
		for (Movie movie : getMoviesByCity(city)) {
			if (movie.getId() == movieId) {
				return movie;
			}
		}
		return null;
	}

	// Get all movies by given name
	@Override
	public Iterable<Movie> getMoviesByName(String name) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		Iterable<Movie> movies = movieRepository.getMovieByNameIgnoreCaseContaining(name);

		if (movies != null) {
			return movies;
		} else {
			throw new MovieNotFoundException("Movie not found");
		}
	}

	@Override
	public Iterable<TicketedEvent> getEventsByName(String name) {
		// TODO Auto-generated method stub
		Iterable<TicketedEvent> events = ticketedEventRepository.getTicketedEventByNameIgnoreCaseContaining(name);
//		for(TicketedEvent event: events) {
//			System.out.println(event.toString());
//		}
		return events;
	}

}
