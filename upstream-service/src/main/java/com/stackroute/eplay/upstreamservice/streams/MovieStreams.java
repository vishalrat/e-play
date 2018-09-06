package com.stackroute.eplay.upstreamservice.streams;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

//Stream defined for writing messages to Message Bus
public interface MovieStreams {
	String INPUT ="movie-in";
	@Output(INPUT)
	MessageChannel inboundEvents();
	
    String OUTPUT = "movie-out";
    @Output(OUTPUT)
    MessageChannel outboundEvents();
}
