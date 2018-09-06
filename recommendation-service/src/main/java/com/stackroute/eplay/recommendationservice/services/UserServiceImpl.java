package com.stackroute.eplay.recommendationservice.services;


import java.util.List;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.stackroute.eplay.recommendationservice.domain.City;

import com.stackroute.eplay.recommendationservice.domain.Genre;

import com.stackroute.eplay.recommendationservice.domain.Movie;
import com.stackroute.eplay.recommendationservice.domain.TicketedEvent;
import com.stackroute.eplay.recommendationservice.domain.User;
import com.stackroute.eplay.recommendationservice.domain.UserKafka;
import com.stackroute.eplay.recommendationservice.repositories.MovieRepository;
import com.stackroute.eplay.recommendationservice.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userrepository;
	private MovieRepository movierepository;

	@Autowired
	public UserServiceImpl(UserRepository userrepository,MovieRepository movierepository) {
		this.userrepository = userrepository;
		this.movierepository = movierepository;
	}
	
	public User saveUser(User user) {
		return userrepository.save(user);		
		
	}
	
	
	public List<Movie> getGenreBasedMoviesForUser(String userName, String cityName){
		return userrepository.getGenreBasedMoviesForUser(userName,cityName);
	}


	public List<TicketedEvent> getTypeBasedTicketedEventsForUser(String userName,String cityName){
		return userrepository.getTypeBasedTicketedEventsForUser(userName,cityName);
	}


	@Override
	public City getCityOfUser(String userName) {
		return userrepository.getCityOfUser(userName);
	}

		 
}
