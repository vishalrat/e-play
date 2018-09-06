package com.stackroute.eplay.userregistration.domain;

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
public class Ticket {
	int ticketedEventId;
	String userName;
	int noOfSeats;
}
