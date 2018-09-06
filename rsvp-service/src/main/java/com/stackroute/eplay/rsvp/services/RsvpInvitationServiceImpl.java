package com.stackroute.eplay.rsvp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.rsvp.domain.Invitation;
import com.stackroute.eplay.rsvp.exceptions.InviteeEmailAlreadyExistException;
import com.stackroute.eplay.rsvp.repositories.RsvpInvitationRepository;


@Service
public class RsvpInvitationServiceImpl implements RsvpInvitationService {

	private RsvpInvitationRepository rsvpInvitationRepository;

	@Autowired
	public RsvpInvitationServiceImpl(RsvpInvitationRepository rsvpInvitationRepository) {
		super();
		this.rsvpInvitationRepository = rsvpInvitationRepository;
	}

	@Autowired
	NextSequenceService nextSequenceService;

	@Override
	public Invitation saveRsvpInvitation(Invitation rsvpInvitation) throws InviteeEmailAlreadyExistException {
		// TODO Auto-generated method stub
		rsvpInvitation.setInvitationId(nextSequenceService.getNextSequence("counter"));
		return rsvpInvitationRepository.save(rsvpInvitation);
	}

	@Override
	public Iterable<Invitation> getAllRsvpInvitation() {
		// TODO Auto-generated method stub
		return rsvpInvitationRepository.findAll();
	}

	@Override
	public Optional<Invitation> getRsvpInvitationByInvitationId(int invitationId) {
		// TODO Auto-generated method stub
		return rsvpInvitationRepository.findById(invitationId);
	}

	@Override
	public boolean deleteRsvpInvitation(int invitationId) {
		// TODO Auto-generated method stub
		if (rsvpInvitationRepository.existsById(invitationId)) {
			rsvpInvitationRepository.deleteById(invitationId);
			return true;
		}
		return false;
	}

	@Override
	public Invitation updateRsvpInvitation(Invitation rsvpInvitation, int invitationId) {
		// TODO Auto-generated method stub
		rsvpInvitation.setInvitationId(invitationId);
		return rsvpInvitationRepository.save(rsvpInvitation);
	}

	@Override
	public Optional<Invitation> getInvitationByInviteeEmail(String inviteeEmail) {
		// TODO Auto-generated method stub
		Optional<Invitation> invitation=rsvpInvitationRepository.getInvitationByInviteeEmail(inviteeEmail);
		return invitation;
	}

}
