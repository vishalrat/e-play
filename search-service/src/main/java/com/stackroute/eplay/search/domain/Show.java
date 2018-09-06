package com.stackroute.eplay.search.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Pojo for Show
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {
	private int showId;
	private int seatRemaining;
	private BigDecimal price;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	private int movieEventId;
	private boolean status;
	// private ArenaLayout layout;
}
