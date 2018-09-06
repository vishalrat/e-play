package com.stackroute.eplay.userregistration.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TheatreStream {
	
	String INPUT = "theatre-in";

	@Input(INPUT)
	SubscribableChannel inboundTheatre();
}
