package com.stackroute.eplay.ticketengine.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MovieEventStream {
	String INPUT = "movie-event-in";
    @Input(INPUT)
    SubscribableChannel inboundMovieEvent();
}