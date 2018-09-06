package com.stackroute.eplay.recommendationservice.domain;

import java.util.List;

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
public class UserKafka {

	@Id
	private String userName;
	private String password;
	//private String fullName;
	private String contactNo;
	private String email;
	private String gender;
	private String city;
	List<Integer> bookedMovieId;
	List<Integer> bookedTicketedEventId;
}
