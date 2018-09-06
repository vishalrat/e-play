package com.stackroute.eplay.ticketengine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InputEmailDetails {
	 private String emailAddress;
	 private String emailBcc[]; 
	 private String subject;
	 private String body;
}
