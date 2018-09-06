package com.stackroute.eplay.recommendationservice.services;

import java.util.List;

import com.stackroute.eplay.recommendationservice.domain.City;
import com.stackroute.eplay.recommendationservice.domain.Movie;
import com.stackroute.eplay.recommendationservice.domain.TicketedEvent;
import com.stackroute.eplay.recommendationservice.domain.User;

public interface UserService {
	public User saveUser(User user);
	public City getCityOfUser(String userName);
	// public User getAllFollowers(String name);
	public List<Movie> getGenreBasedMoviesForUser(String userName,String cityName);
	public List<TicketedEvent> getTypeBasedTicketedEventsForUser(String userName,String cityName);
//	public User getAllFollowers(String name);

}
