package com.stackroute.eplay.search.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.eplay.search.domain.TicketedEvent;

public interface TicketedEventRepository extends MongoRepository<TicketedEvent, Long> {
	public Iterable<TicketedEvent> getTicketedEventByNameIgnoreCaseContaining(@Param("name") String name);

}
