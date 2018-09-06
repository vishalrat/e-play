package com.stackroute.eplay.rsvp.services;

import java.util.Optional;

import com.stackroute.eplay.rsvp.domain.Invitation;
import com.stackroute.eplay.rsvp.exceptions.InviteeEmailAlreadyExistException;

public interface RsvpInvitationService {
	
public Invitation saveRsvpInvitation(Invitation rsvpInvitation) throws InviteeEmailAlreadyExistException;
	
	public Iterable<Invitation> getAllRsvpInvitation();
	
	public Optional<Invitation> getRsvpInvitationByInvitationId(int invitationId);
	
	public boolean deleteRsvpInvitation(int invitationId);
	
	public Invitation updateRsvpInvitation (Invitation rsvpInvitation,int invitationId);
	
	public Optional<Invitation> getInvitationByInviteeEmail(String inviteeEmail);
}
