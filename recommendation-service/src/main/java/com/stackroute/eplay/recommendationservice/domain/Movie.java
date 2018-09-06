package com.stackroute.eplay.recommendationservice.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Movie {
	@Id
	private int id;
	private String name;
	private String language;
	private String backGroundPoster;
	private String cardPoster;
	
	private int rating;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate releaseDate;
	
	@Relationship(type="RELEASED_IN",direction=Relationship.OUTGOING)
	private List<City> cities ;
	
	@Relationship(type="IS_OF_GENRE",direction=Relationship.OUTGOING)
	private Genre genre;
	
	//@JsonIgnoreProperties("movie")
	@Relationship(type="VIEWED",direction=Relationship.INCOMING)
	private List<User> user;
	
	public Movie(int id, String name, String language,String backGroundPoster, String cardPoster,int rating, Genre genre, LocalDate releaseDate) {
		this.id = id;
		this.name = name;
		this.language = language;
		this.backGroundPoster = backGroundPoster;
		this.cardPoster = cardPoster;
		this.rating = rating;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}
	public void releasedIn(City city) {
		cities.add(city);
	}
	/*public Movie(int movieId, String title, String language, int rating) {
		this.id = movieId;
		this.name = title;
		this.language = language;
		this.rating = rating;
	}	*/
	
		
}
