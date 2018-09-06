package com.stackroute.eplay.recommendationservice.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

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
public class MovieKafka {
    
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate releaseDate;

}