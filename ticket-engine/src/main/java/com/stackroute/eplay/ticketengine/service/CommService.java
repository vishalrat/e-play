package com.stackroute.eplay.ticketengine.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.stackroute.eplay.ticketengine.domain.BlockedSeats;

class CommService {

	private static SimpMessagingTemplate template;

	public static void setTemplate(SimpMessagingTemplate tmplt) {
		template = tmplt;
	}

	public static void send(BlockedSeats message) {
		template.convertAndSend("/chat", message);
	}
}