package com.stackroute.eplay.rsvp.repositories;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.rsvp.domain.Invitation;

@Repository //
public interface RsvpInvitationRepository extends MongoRepository<Invitation,Integer> {
public Optional<Invitation> getInvitationByInviteeEmail(@Param("inviteeEmail") String inviteeEmail);
}
