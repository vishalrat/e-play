package com.stackroute.eplay.upstreamservice.streams;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

//Stream defined for writing messages to Message Bus
public interface TicketedEventStreams {

	String OUTPUT = "ticketed-event-out";
    @Output(OUTPUT)
    MessageChannel outboundEvents();
}
