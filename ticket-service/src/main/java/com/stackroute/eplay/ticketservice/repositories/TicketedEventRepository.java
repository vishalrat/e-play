package com.stackroute.eplay.ticketservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.stackroute.eplay.ticketservice.domain.TicketedEvent;

public interface TicketedEventRepository extends MongoRepository<TicketedEvent,Integer>{
public TicketedEvent getTicketedEventById(int ticketedEventId);
}
