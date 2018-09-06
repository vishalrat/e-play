package com.stackroute.eplay.userregistration.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EmailStream {
	String OUTPUT = "email-out";
    @Output(OUTPUT)
    MessageChannel outboundEmail();
}
