package com.stackroute.eplay.ticketservice.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TheatreStreams {
	String INPUT = "theatre-in";
    @Input(INPUT)
    SubscribableChannel inboundTheatre();
    String OUTPUT = "theatre-out";
    @Output(OUTPUT)
    MessageChannel outboundTheatre();
}