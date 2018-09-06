package com.stackroute.eplay.rsvp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.rsvp.domain.Invitation;
import com.stackroute.eplay.rsvp.domain.RSVPEvent;
import com.stackroute.eplay.rsvp.repositories.RsvpCreateRepository;

@Service
public class RsvpCreateServiceImpl implements RsvpCreateService{
	
	
	private RsvpCreateRepository rsvpCreateRepository;
	@Autowired
	NextSequenceService nextSequenceService;

	@Autowired
	public RsvpCreateServiceImpl(RsvpCreateRepository rsvpCreateRepository) {
		super();
		this.rsvpCreateRepository = rsvpCreateRepository;
	}

	
	public RSVPEvent saveRsvpCreate(RSVPEvent rsvpCreate) {
		// TODO Auto-generated method stub
	//	rsvpCreate.setId(nextSequenceService.getNextSequence("counter"));
		return rsvpCreateRepository.save(rsvpCreate);
	}


	public Iterable<RSVPEvent> getAllRsvpCreate() {
		// TODO Auto-generated method stub
		return rsvpCreateRepository.findAll();
	}


	public Optional<RSVPEvent> getRsvpCreateById(int id) {
		// TODO Auto-generated method stub
		if(rsvpCreateRepository.existsById(id))
		{
			return rsvpCreateRepository.findById(id);
		}
		return null;
	}

	@Override
	public boolean deleteRsvpCreate(int id) {
		// TODO Auto-generated method stub
		if(rsvpCreateRepository.existsById(id))
		{
			rsvpCreateRepository.deleteById(id);
			return true;
		}
		else
		return false;
	}

	
	public RSVPEvent updateRSVPEvent(Invitation invitation, int id) {
		// TODO Auto-generated method stub
		RSVPEvent rsvpEvent=getRsvpCreateById(id).get();
		invitation.setInvitationId(nextSequenceService.getNextSequence("counter"));
		List<Invitation> invitiesList=rsvpEvent.getRsvpInvitation();
		if(invitiesList==null) {
			invitiesList=new ArrayList<Invitation>();
		}
		Invitation tempInvitation = null;
		for(Invitation currInvitation: invitiesList) {
			if(currInvitation.getInviteeEmail().equals(invitation.getInviteeEmail()))
				tempInvitation = currInvitation;
		}
		if(tempInvitation != null)
			invitiesList.remove(tempInvitation);
		invitiesList.add(invitation);
		rsvpEvent.setRsvpInvitation(invitiesList);
		return rsvpCreateRepository.save(rsvpEvent);

	}

}
