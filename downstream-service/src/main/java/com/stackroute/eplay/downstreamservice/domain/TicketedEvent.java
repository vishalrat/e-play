package com.stackroute.eplay.downstreamservice.domain;

import java.sql.Date;

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
	private String userName;
	public Date date;
	public Date time;
	public String city;
	public String location;
	public String poster;
	public String type;
	public int capacity;
	public int remainingSeats;
	public String description;
	public String price;
	public String performers;
	public String termsAndConditions;
	public String notes;

}
