package com.stackroute.eplay.rsvp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {

	@Id
	private int invitationId; // unique
	private int eventId; // id from Event table
	private String inviteeUserName; // from user register table
	private String inviteeName; // from user register table
	private String inviteeEmail; // from user register table
	private String inviteePhoneNo;// from user register table
	private boolean status;
	private String senderUserName;

}
