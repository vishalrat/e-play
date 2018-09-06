package com.stackroute.eplay.upstreamservice.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

	@Id
	private int id;
	private String name;
	private String backGroundPoster;
	private String cardPoster;
	private String genre;
	private String language;
	private String reviews;
	private String cast;
	private String description;
	private int rating;
	private int duration;
	private List<MovieEvent> movieEvents;
	private LocalDate releaseDate;
}
