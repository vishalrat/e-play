//package com.stackroute.eplay.search.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.ContextRefreshedEvent;
//
//import com.stackroute.eplay.search.domain.City;
//import com.stackroute.eplay.search.domain.Movie;
//import com.stackroute.eplay.search.domain.TicketedEvent;
//import com.stackroute.eplay.search.repositories.CityRepository;
//import com.stackroute.eplay.search.repositories.MovieRepository;
//import com.stackroute.eplay.search.repositories.TicketedEventRepository;
//
//@Configuration
//public class BootStrapDB implements ApplicationListener<ContextRefreshedEvent> {
//	// public class BootStrapDB implements CommandLineRunner{
//
//	private CityRepository cityRepo;
//	@SuppressWarnings("unused")
//	private MovieRepository movieRepo;
//	private TicketedEventRepository eventRepo;
//
//	@Autowired
//	public BootStrapDB(CityRepository cityRepo, MovieRepository movieRepo, TicketedEventRepository eventRepo) {
//		this.cityRepo = cityRepo;
//		this.movieRepo = movieRepo;
//		this.eventRepo = eventRepo;
//
//	}
//
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//
////		Movie movie1 = new Movie(1, "1", "1");
////		Movie movie2 = new Movie(2, "2", "2");
////		Movie movie3 = new Movie(3, "3", "3");
////		Movie movie4 = new Movie(4, "4", "4");
////		Movie movie5 = new Movie(5, "5", "5");
////		Movie movie6 = new Movie(6, "6", "6");
////		Movie movie7 = new Movie(7, "7", "7");
////		
////		movieRepo.save(movie1);
////		movieRepo.save(movie2);
////		movieRepo.save(movie3);
////		movieRepo.save(movie4);
////		movieRepo.save(movie5);
////		movieRepo.save(movie6);
////		movieRepo.save(movie7);
////		
////		List<Movie>list1 = new ArrayList<Movie>();
////		list1.addAll(Arrays.asList(movie1, movie2, movie3));
////		
////		List<Movie>list2 = new ArrayList<Movie>();
////		list2.addAll(Arrays.asList(movie4, movie5));
////		
////		List<Movie>list3 = new ArrayList<Movie>();
////		list3.addAll(Arrays.asList(movie6, movie7));
////		
////		City city1 = new City("delhi", list1);
////		City city2 = new City("mumbai", list2);
////		City city3 = new City("bangalore", list3);
////		
////		cityRepo.save(city1);
////		cityRepo.save(city2);
////		cityRepo.save(city3);
//
//		TicketedEvent ticketedEvent1 = new TicketedEvent(777, "ticketed event 1", null, null, "mumbai", null, null,
//				null, 100, 100, null, null, null, null, null, null);
//		TicketedEvent ticketedEvent2 = new TicketedEvent(778, "ticketed event 2", null, null, "mumbai", null, null,
//				null, 100, 100, null, null, null, null, null, null);
//
//		List<TicketedEvent> ticketedEventList1 = new ArrayList<TicketedEvent>();
//		ticketedEventList1.add(ticketedEvent1);
//		ticketedEventList1.add(ticketedEvent2);
//
//		Movie movie1 = new Movie(1, "dunkirk", "1", null, null, null, null, "kjkhkj", 0, 0, null, null, null);
//		Movie movie2 = new Movie(2, "spiderman", "1", null, null, null, null, "abcdef", 0, 0, null, null, null);
//		Movie movie3 = new Movie(3, "batman", "1", null, null, null, null, "lol", 0, 0, null, null, null);
//		Movie movie4 = new Movie(4, "superman", "1", null, null, null, null, "123", 0, 0, null, null, null);
//
//		// Movie movie2 = new Movie(2, "movie1", "2", null, null, null, null, null, 0,
//		// 0, null, null, null);
//		// Movie movie3 = new Movie(3, "spiderman", "3", null, null, null, null, null,
//		// 0, 0, null, null, null);
//		movie1.setPoster(
//				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXIQwwW7u0f5LL-qPk0IF2RrPqdEhmMl1JHY9nd1-ZFeVLRq6ygw");
//		movie2.setPoster(
//				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8vAl8ZSWzc87lwVHBl3UzOjdizeemQahgYXHFFD2MZYChKzwy");
//		movie3.setPoster(
//				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1BHFbPMqoKRwI14fWQO5_8G9O4d1ZsGSkuP75e8bSXGMr0uau");
//		// Movie movie4 = new Movie(4, "some one", "1", null, null, null, null, null, 0,
//		// 0, null, null, null);
//		Movie movie5 = new Movie(5, "movie x", "1", null, null, null, null, null, 0, 0, null, null, null);
//		Movie movie6 = new Movie(6, "x men", "1", null, null, null, null, null, 0, 0, null, null, null);
//		Movie movie7 = new Movie(7, "y men", "1", null, null, null, null, null, 0, 0, null, null, null);
//		Movie movie8 = new Movie(8, "z men", "1", null, null, null, null, null, 0, 0, null, null, null);
//		movie4.setPoster(
//				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPg_RTDdLvJXxkDyXIEt_g7jZhW-8El-N6MpYO3NOP7v4Ke6Fs");
//		movie5.setPoster(
//				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUSfmdPO6Bg5kmfvWNrCFcERY-Mv-aZHsQzsVmTVQEvu3RZjCP");
//		movie6.setPoster(
//				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPg_RTDdLvJXxkDyXIEt_g7jZhW-8El-N6MpYO3NOP7v4Ke6Fs");
//		movie7.setPoster(
//				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTp726spazkEswmUlQNBhMivFKWXOh31ivSu4S92QUYnbJG5vtS");
//		movie8.setPoster(
//				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkqs7cylAMJ3TeKDzxtlMibqUMsYof1wNG0C0oSol0blDFtjqm");
//
//		List<Movie> movieList1 = new ArrayList<Movie>();
//		movieList1.add(movie1);
//		movieList1.add(movie3);
//
//		List<Movie> movieList2 = new ArrayList<Movie>();
//		movieList2.add(movie2);
//		movieList2.add(movie3);
//
//		List<Movie> movieList3 = new ArrayList<Movie>();
//		movieList3.add(movie4);
//		movieList3.add(movie5);
//
//		List<Movie> movieList4 = new ArrayList<Movie>();
//		movieList4.add(movie8);
//		movieList4.add(movie6);
//		List<TicketedEvent> eventList = new ArrayList<TicketedEvent>();
//
//		City city1 = new City("mumbai", movieList1, ticketedEventList1);
//		City city2 = new City("delhi", movieList2, eventList);
//		City city3 = new City("hyderabad", movieList3, eventList);
//		City city4 = new City("bangalore", movieList4, eventList);
//
//		movieRepo.saveAll(movieList1);
//		movieRepo.saveAll(movieList2);
//		movieRepo.saveAll(movieList3);
//		movieRepo.saveAll(movieList4);
//
//		eventRepo.save(ticketedEvent1);
//		eventRepo.save(ticketedEvent2);
//
//		cityRepo.save(city1);
//		cityRepo.save(city2);
//		cityRepo.save(city3);
//		cityRepo.save(city4);
//
//	}
//
//}