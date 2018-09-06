package com.stackroute.eplay.ticketservice.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ShowStreams {
	String INPUT = "show-in";
    @Input(INPUT)
    SubscribableChannel inboundShow();
}
