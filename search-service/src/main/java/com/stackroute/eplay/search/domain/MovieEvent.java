package com.stackroute.eplay.search.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Pojo for Movie Event
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieEvent {
	private int movieEventId;
	private int movieId;
	private int theatreId;
	private int showCount;
	private List<Show> shows;
	private String city;
	private String userName;
	private int week;

}
