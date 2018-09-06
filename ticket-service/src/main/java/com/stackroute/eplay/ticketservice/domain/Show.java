package com.stackroute.eplay.ticketservice.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

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
    private int seatRemaining;
    private int price;
    private LocalTime startTime;
    private LocalDate date;
    private int movieEventId;
    private boolean status;
    private Map<Integer, String> seats;
    
}