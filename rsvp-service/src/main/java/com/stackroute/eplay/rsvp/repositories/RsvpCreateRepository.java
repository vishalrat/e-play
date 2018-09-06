package com.stackroute.eplay.rsvp.repositories;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.rsvp.domain.RSVPEvent;

@Repository // 
public interface RsvpCreateRepository extends MongoRepository<RSVPEvent, Integer> {

}
