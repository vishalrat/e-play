package com.stackroute.eplay.rsvp.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.stackroute.eplay.rsvp.domain.RSVPEvent;
import com.stackroute.eplay.rsvp.services.RsvpCreateServiceImpl;
import com.stackroute.eplay.rsvp.streams.RSVPEventStreams;

@EnableBinding(RSVPEventStreams.class)
public class KafkaListener {

	private RsvpCreateServiceImpl rsvpCreateServiceImpl;

	@Autowired
	public KafkaListener(RsvpCreateServiceImpl rsvpCreateServiceImpl) {
		super();
		this.rsvpCreateServiceImpl = rsvpCreateServiceImpl;
	}

	@StreamListener(RSVPEventStreams.INPUT)
	public void postEvents(@Payload RSVPEvent resvpEvent) {
		rsvpCreateServiceImpl.saveRsvpCreate(resvpEvent);
	}
}