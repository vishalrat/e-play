package com.stackroute.eplay.userregistration.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Model class for the RSVPEvents
 */

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RSVPEvent {

	@Id
	private String id;
	private String userName;
	private String name;
	private Date startDate;
	private String startTime;
	private String venue;
	private String poster;
	private String description;
	private int numberOfAttendees;
	private boolean isCompleted;
	private List<Invitation> rsvpInvitation;

}
