package com.stackroute.eplay.search.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MovieEventStream {
	String INPUT = "updated-movie-event-in";

	@Input(INPUT)
	SubscribableChannel inboundMovieEvent();
}