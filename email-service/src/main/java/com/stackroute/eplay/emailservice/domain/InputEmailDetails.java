package com.stackroute.eplay.emailservice.domain;

import java.util.Arrays;
import java.util.List;

public class InputEmailDetails {
// private String name;
 

 
 private String emailAddress;
 
 private String emailBcc[]; 
 
 private String subject;
 
 private String body;
 
 


public InputEmailDetails( String emailAddress, String[] emailBcc, String subject,String body) {
	super();
	//this.name = name;
	//this.lastName = lastName;
	this.emailAddress = emailAddress;
	this.emailBcc = emailBcc;
	this.subject = subject;
	this.body=body;
	
}


public String getBody() {
	return body;
}


public void setBody(String body) {
	this.body = body;
}



public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public InputEmailDetails() {
	 
	 
 }


@Override
public String toString() {
	return "User [emailAddress=" + emailAddress + ", emailBcc="
			+ Arrays.toString(emailBcc) + ", subject=" + subject + "]";
}



public String getEmailAddress() {
	return emailAddress;
}

public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}

public String[] getEmailBcc() {
	return emailBcc;
}

public void setEmailBcc(String emailBcc[]) {
	this.emailBcc = emailBcc;
}
 
}
