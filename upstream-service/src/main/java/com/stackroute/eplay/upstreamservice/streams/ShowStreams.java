package com.stackroute.eplay.upstreamservice.streams;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

//Stream defined for writing messages to Message Bus
public interface ShowStreams {
	String INPUT ="show-in";
	@Output(INPUT)
	MessageChannel inboundEvents();
	
	String OUTPUT = "show-out";
    @Output(OUTPUT)
    MessageChannel outboundEvents();
}
