package com.stackroute.eplay.userregistration.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TicketedEventStream {
	
	String INPUT = "ticketed-event-in";

	@Input(INPUT)
	SubscribableChannel inboundTheatre();
}
