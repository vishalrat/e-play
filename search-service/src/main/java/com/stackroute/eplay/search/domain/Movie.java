package com.stackroute.eplay.search.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Pojo for Movie
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	private float rating;
	private float duration;
	private List<MovieEvent> movieEvents;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate releaseDate;
	private List<Theatre> theatres;

}
