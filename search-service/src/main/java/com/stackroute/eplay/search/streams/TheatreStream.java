package com.stackroute.eplay.search.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TheatreStream {
	String INPUT = "theatre-in";

	@Input(INPUT)
	SubscribableChannel inboundTheatre();
}