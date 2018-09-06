package com.stackroute.eplay.ticketservice.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TicketedEventStreams {
	String INPUT = "ticketed-event-in";
    @Input(INPUT)
    SubscribableChannel inboundTicketedEvent();
    String OUTPUT = "ticketed-event-out";
    @Output(OUTPUT)
    MessageChannel outboundTicketedEvent();
}