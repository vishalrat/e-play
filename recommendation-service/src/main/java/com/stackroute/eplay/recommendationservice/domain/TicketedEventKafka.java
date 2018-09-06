package com.stackroute.eplay.recommendationservice.domain;

import java.util.Date;

import org.neo4j.ogm.annotation.Id;

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
public class TicketedEventKafka {
	
	@Id
	private int id;
	private String name;
	private Date date;
	private Date time;
	private String city;
	private String location;
	private String backGroundPoster;
	private String cardPoster;
	private String type;
	private int capacity;
	private int remainingSeats;
	private String description;
	private String price;
	private String performers;
	private String termsAndConditions;
	private String notes;
	private String userName;
}
