package com.stackroute.eplay.rsvp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.eplay.rsvp.domain.Invitation;
import com.stackroute.eplay.rsvp.domain.RSVPEvent;
import com.stackroute.eplay.rsvp.exceptions.InviteeEmailAlreadyExistException;
import com.stackroute.eplay.rsvp.services.RsvpCreateService;
import com.stackroute.eplay.rsvp.services.RsvpInvitationService;
import com.stackroute.eplay.rsvp.streams.RSVPEventStreams;

import lombok.NoArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@EnableBinding(RSVPEventStreams.class)
@NoArgsConstructor
public class InvitationController {

	private RsvpInvitationService rsvpInvitationService;
	private RsvpCreateService rsvpCreateService;
	private RSVPEventStreams rsvpEventStreams;

	@Autowired
	public InvitationController(RsvpInvitationService rsvpInvitationService,
			RsvpCreateService rsvpCreateService, RSVPEventStreams rsvpEventStreams) {
		super();
		this.rsvpInvitationService = rsvpInvitationService;
		this.rsvpCreateService = rsvpCreateService;
		this.rsvpEventStreams = rsvpEventStreams;
	}
	

	@PostMapping("/invitation")
	public ResponseEntity<?> saveInvitation(@RequestBody Invitation rsvpInvitation) {
		RSVPEvent rsvpEvent = rsvpCreateService.getRsvpCreateById(rsvpInvitation.getEventId()).get();
		List<Invitation> invitations;
		if (rsvpEvent.getRsvpInvitation() == null)
			invitations = new ArrayList<>();
		else
			invitations = rsvpEvent.getRsvpInvitation();
		invitations.add(rsvpInvitation);
		rsvpEvent.setRsvpInvitation(invitations);
		rsvpCreateService.saveRsvpCreate(rsvpEvent);
		MessageChannel messageChannel = rsvpEventStreams.outboundRSVPEvent();
		messageChannel.send(MessageBuilder.withPayload(rsvpEvent)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
		try {
		return new ResponseEntity<Invitation>(rsvpInvitationService.saveRsvpInvitation(rsvpInvitation),
				HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/invitations")
	public ResponseEntity<?> getAllInvitations() {
		return new ResponseEntity<Iterable<Invitation>>(rsvpInvitationService.getAllRsvpInvitation(),
				HttpStatus.OK);
	}

	@GetMapping("/invitation/{invitationId}")
	public ResponseEntity<?> getInvitationById(@PathVariable int invitationId) {
		return new ResponseEntity<Optional<Invitation>>(
				rsvpInvitationService.getRsvpInvitationByInvitationId(invitationId), HttpStatus.OK);
	}

	@DeleteMapping(path = "/invitation/{invitationId}")
	public ResponseEntity<?> deleteInvitation(@PathVariable int invitationId) {
		rsvpInvitationService.deleteRsvpInvitation(invitationId);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

//	@PutMapping("/invitation/{invitationId}")
//	public ResponseEntity<?> updateInvitation(@PathVariable int invitationId, @RequestBody Invitation rsvpInvitation) {
//		return new ResponseEntity<Invitation>(
//				rsvpInvitationService.updateRsvpInvitation(rsvpInvitation, invitationId), HttpStatus.OK);
//	}
	
	@GetMapping("/invitation/{inviteeEmail}")
	public ResponseEntity <?> getInvitationByInviteeEmail(@PathVariable String inviteeEmail)
	{
		return new ResponseEntity<Optional<Invitation>>(rsvpInvitationService.getInvitationByInviteeEmail(inviteeEmail),HttpStatus.OK);
	}
}
