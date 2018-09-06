package com.stackroute.eplay.rsvp.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface RSVPEventStreams {
	String INPUT = "rsvp-event-in";
	@Input(INPUT)
	SubscribableChannel inboundRSVPEvent();
	String OUTPUT = "rsvp-event-out";
	@Output(OUTPUT)
	MessageChannel outboundRSVPEvent();
}