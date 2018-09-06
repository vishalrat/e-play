package com.stackroute.eplay.ticketservice.exception;

public class TicketedEventAlreadyExistException extends Exception{
	private static final long serialVersionUID = 1L;

	public TicketedEventAlreadyExistException(String message) {
		super(message);
	}
}
