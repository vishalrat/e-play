package com.stackroute.eplay.userregistration.domain;

import java.time.LocalDate;
import java.util.Date;

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
public class TicketedEvent {

	@Id
	public int id;
	public String name;
	public String userName;
	public String localDate;
	public Date date;
	public String city;
	public String location;
	public String backGroundPoster;
	public String cardPoster;
	public int capacity;
	public String description;
	public String price;
	
}

