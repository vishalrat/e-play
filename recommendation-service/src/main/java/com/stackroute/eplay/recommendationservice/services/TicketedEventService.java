package com.stackroute.eplay.recommendationservice.services;
import java.util.List;

import com.stackroute.eplay.recommendationservice.domain.Movie;
import com.stackroute.eplay.recommendationservice.domain.TicketedEvent;

public interface TicketedEventService {
	public TicketedEvent saveTicketedEvent(TicketedEvent ticketedevent);
	public TicketedEvent findByName(String name);
	public TicketedEvent findById(int id);
	public void hostedIn(String cityName,int id);
	public List<TicketedEvent> getTicketedEventsByType(String categoryName);
    public List<TicketedEvent> getTicketedEventsByCity(String name);
    public List<TicketedEvent> getTicketedEventByCityType(String name,String categoryName);
}
