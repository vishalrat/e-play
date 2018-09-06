package com.stackroute.eplay.ticketengine.streams;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BookedSeatsStream {
	String OUTPUT = "booked-seats-out";
    @Output(OUTPUT)
    MessageChannel outboundBookedSeats();
}
