package com.stackroute.eplay.recommendationservice.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserStreams {
	String INPUT = "user-in";
    @Input(INPUT)
    SubscribableChannel inboundUser();
}