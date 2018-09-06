package com.stackroute.eplay.recommendationservice.domain;

import org.springframework.data.annotation.Id;

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
public class MovieEvent {

	
    @Id
    private int movieEventId;
    private int movieId;
    private int theatreId;
   // private int showCount;
    private String city;
    private String userName;
    //private int week;    
}
