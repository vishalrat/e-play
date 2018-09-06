package com.stackroute.eplay.upstreamservice.domain;


import java.util.Date;
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
public class RSVPEvent {

	@Id
	private int id;
	private String name;
	private Date startDate;
	private Date startTime;
	private Date endDate;
	private Date endTime;
	private String city;
	private String location;
	private String poster;
	private String type;
	private String description;
	private String performers;
	private String termsAndConditions;
	private String notes;
	private int numberOfAttendees;
	private boolean isCompleted;
	private List<invitation> rsvpInvitation;
	private String userName;

}