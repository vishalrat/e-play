package com.stackroute.eplay.ticketservice.streams;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FinalMovieEventStreams {

	String OUTPUT = "final-movie-event-out";

	@Output(OUTPUT)
	MessageChannel outboundfinalMovieEvent();
}
