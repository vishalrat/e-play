package com.stackroute.eplay.userregistration.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Model class for the Invitation
 */

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {
	@Id
	private String invitationId;
	private String eventId;
	private String inviteeUserName;
	private String inviteeName;
	private String inviteeEmail;
	private String inviteePhoneNo;
	private boolean status;
}