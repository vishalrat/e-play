package com.stackroute.eplay.search.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MovieStream {
	String INPUT = "movie-in";

	@Input(INPUT)
	SubscribableChannel inboundMovie();
}