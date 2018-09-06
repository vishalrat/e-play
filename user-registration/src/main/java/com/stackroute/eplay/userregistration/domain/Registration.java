package com.stackroute.eplay.userregistration.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Model class for the User Registration
 */

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
	@Id
	private String userName;
	private String password;
	private String fullName;
	private String contactNo;
	private String email;
	private String gender;
	private String city;
	private List<Theatre> theatres;
	private List<RSVPEvent> rsvpEvents;
	private List<MovieEvent> movieEvent;
	private List<TicketedEvent> ticketedEvent;
	private List<Integer> bookedMovieId;
	private List<Integer> bookedTicketedEventId;

}