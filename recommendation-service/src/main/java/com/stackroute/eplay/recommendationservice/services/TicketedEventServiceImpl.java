package com.stackroute.eplay.recommendationservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.recommendationservice.domain.Movie;
import com.stackroute.eplay.recommendationservice.domain.TicketedEvent;
import com.stackroute.eplay.recommendationservice.repositories.TicketedEventRepository;
@Service
public class TicketedEventServiceImpl implements TicketedEventService {
	private TicketedEventRepository ticketedEventRepository;
	
	@Autowired
	public TicketedEventServiceImpl(TicketedEventRepository ticketedEventRepository) {
		super();
		this.ticketedEventRepository = ticketedEventRepository;
	}
		
	public TicketedEvent saveTicketedEvent(TicketedEvent ticketedevent) {
		return ticketedEventRepository.save(ticketedevent);
	}

	@Override
	public TicketedEvent findById(int id) {
		return ticketedEventRepository.findById(id);
	}

	@Override
	public TicketedEvent findByName(String name) {
		return ticketedEventRepository.findByName(name);
	}

	@Override
	public void hostedIn(String cityName,int id) {
		ticketedEventRepository.hostedIn(cityName, id);		
	}
	
	@Override
	public List<TicketedEvent> getTicketedEventsByType(String categoryName) {
		return ticketedEventRepository.getTicketedEventsByType(categoryName);
	}
	
	@Override
	public List<TicketedEvent> getTicketedEventsByCity(String name) {
		return ticketedEventRepository.getTicketedEventsByCity(name);
	}

	@Override
	public List<TicketedEvent> getTicketedEventByCityType(String name, String categoryName) {
		return ticketedEventRepository.getTicketedEventByCityType(name,categoryName);
	}
}
