package com.stackroute.eplay.ticketengine.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

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
@RedisHash("BlockedSeats")
public class BlockedSeats{
	@Id
	private String id;
	private Long showId;
	private List<Integer> seats;
	private String status;
	private String userName;
	private String guestUserEmailId;
	private int movieEventId;
}
