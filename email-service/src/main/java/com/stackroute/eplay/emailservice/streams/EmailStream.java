package com.stackroute.eplay.emailservice.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EmailStream {
	String INPUT = "email-in";
    @Input(INPUT)
    SubscribableChannel inboundEmail();
}
