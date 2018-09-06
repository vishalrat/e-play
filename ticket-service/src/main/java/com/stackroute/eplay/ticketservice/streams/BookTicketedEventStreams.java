package com.stackroute.eplay.ticketservice.streams;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BookTicketedEventStreams {
	String OUTPUT = "ticketed-event-ticket-out";

	@Output(OUTPUT)
	MessageChannel outboundTicketedEventTicket();
}
