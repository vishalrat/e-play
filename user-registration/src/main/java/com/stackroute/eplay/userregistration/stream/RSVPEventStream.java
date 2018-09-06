package com.stackroute.eplay.userregistration.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RSVPEventStream {
	String INPUT = "rsvp-event-in";

	@Input(INPUT)
	SubscribableChannel inboundRSVPEvent();
}
