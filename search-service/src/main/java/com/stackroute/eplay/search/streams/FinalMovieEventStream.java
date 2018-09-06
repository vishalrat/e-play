package com.stackroute.eplay.search.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface FinalMovieEventStream {
	String INPUT = "final-movie-event-in";

	@Input(INPUT)
	SubscribableChannel inboundFinalMovieEvent();
}