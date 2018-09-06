package com.stackroute.eplay.downstreamservice.domain;

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
public class Show {

	@Id
	private int showId;
	private int price;
	private int seatRemaining;
	private Date startTime;
	private Date date;
	private int movieEventId;
}
