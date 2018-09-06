package com.stackroute.eplay.downstreamservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.downstreamservice.domain.TicketedEvent;

@Repository
public interface TicketedEventRepository extends MongoRepository<TicketedEvent, Integer> {
}
