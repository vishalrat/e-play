package com.stackroute.eplay.userregistration.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BookTicketedEventStream {
	String INPUT = "ticketed-event-ticket-in";

	@Input(INPUT)
	SubscribableChannel inboundTicketedEventTicket();
}
