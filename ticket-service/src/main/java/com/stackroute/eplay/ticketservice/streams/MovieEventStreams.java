package com.stackroute.eplay.ticketservice.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MovieEventStreams {
	String INPUT = "movie-event-in";
    @Input(INPUT)
    SubscribableChannel inboundMovieEvent();
    String OUTPUT = "movie-event-out";
    @Output(OUTPUT)
    MessageChannel outboundMovieEvent();
}