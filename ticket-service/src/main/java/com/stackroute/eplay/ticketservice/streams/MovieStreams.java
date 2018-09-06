package com.stackroute.eplay.ticketservice.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MovieStreams {
	String INPUT = "movie-in";
    @Input(INPUT)
    SubscribableChannel inboundMovie();
    String OUTPUT = "movie-out";
    @Output(OUTPUT)
    MessageChannel outboundMovie();
}