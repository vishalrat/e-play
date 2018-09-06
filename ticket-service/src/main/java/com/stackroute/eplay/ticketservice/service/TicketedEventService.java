package com.stackroute.eplay.ticketservice.service;

import java.util.List;

import com.stackroute.eplay.ticketservice.domain.Ticket;
import com.stackroute.eplay.ticketservice.domain.TicketedEvent;
import com.stackroute.eplay.ticketservice.exception.TicketedEventAlreadyExistException;

public interface TicketedEventService {

	public TicketedEvent saveTicketedEvent(TicketedEvent ticketedEvent) throws TicketedEventAlreadyExistException;
	public List<TicketedEvent> getAllTicketedEvent();
	public TicketedEvent getTicketedEventById(int id);
	public TicketedEvent updateTicketedEvent(TicketedEvent ticketedEvent);
	public Ticket bookTicketedEvent(Ticket ticket);
}
