package com.stackroute.eplay.upstreamservice.domain;

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
public class invitation {

	@Id
	private int invitationId;
	private int eventId;
	private String inviteeUserName;
	private String inviteeName;
	private String inviteeEmail;
	private String inviteePhoneNo;
	private boolean status;
}
