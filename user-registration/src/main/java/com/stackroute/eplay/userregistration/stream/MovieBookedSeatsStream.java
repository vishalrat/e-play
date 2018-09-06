package com.stackroute.eplay.userregistration.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MovieBookedSeatsStream {
	String INPUT = "booked-movie-seats-in";
    @Input(INPUT)
    SubscribableChannel inboundBookedSeats();
}
