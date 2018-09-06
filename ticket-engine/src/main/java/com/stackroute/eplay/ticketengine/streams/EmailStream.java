package com.stackroute.eplay.ticketengine.streams;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EmailStream {
	String OUTPUT = "email-out";
    @Output(OUTPUT)
    MessageChannel outboundEmail();
}
