package com.stackroute.eplay.search.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Pojo for City
@Document(indexName = "city", type = "city")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
	@Id
	private String city;
	private List<Movie> movieList;
	private List<TicketedEvent> ticketedEventsList;
}
