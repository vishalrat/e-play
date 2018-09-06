package com.stackroute.eplay.userregistration.domain;

import java.util.List;

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
public class BookedMovieTickets{
	private String id;
	private Long showId;
	private List<Integer> seats;
	private String status;
	private String userName;
	private String guestUserEmailId;
	private int movieEventId;
}
