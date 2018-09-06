package com.stackroute.eplay.downstreamservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.downstreamservice.domain.RSVPEvent;

@Repository
public interface RSVPEventRepository extends MongoRepository<RSVPEvent, Integer> {
}
