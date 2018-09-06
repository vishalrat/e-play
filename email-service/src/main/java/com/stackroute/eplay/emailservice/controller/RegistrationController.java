package com.stackroute.eplay.emailservice.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.eplay.emailservice.domain.InputEmailDetails;
import com.stackroute.eplay.emailservice.service.EmailNotificationService;

@RestController
@RequestMapping("/api/v1/email")
@CrossOrigin
public class RegistrationController {
	
	private Logger logger=LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	public EmailNotificationService notificationService;
	
//	@RequestMapping("/signup")
//	public String signup() {
//		return "Sign Up";
//	}
	
	@PostMapping("/sendEmail")
		public String sendInvitations(@RequestBody InputEmailDetails inputEmailDetails) throws AddressException, MessagingException {
//		String email1="garvitgarg88@gmail.com";
//		String email2="skumarvit3@gmail.com";
//		String email3="manutyagi4@gmail.com";
//		String[] emailList= {email2,email1,email3};
		
	//emailList.add(email2);
//		emailList.add(email1);
//		emailList.add(email3);
//		InputEmailDetails user= new InputEmailDetails();
//		user.setFirstName("Vishal");
//		user.setLastName("Rathor");
//		user.setEmailAddress("aerospacevishal@gmail.com");
//		user.setEmailBcc(emailList);
//		user.setSubject("your RSVP Event created");  //Enter event subject
//		user.setText("Are you coming for the Event?");  // Enter text
		//user.setEmailCc("manutyagi4@gmail.com");
		//user.setEmailCc("aerospacevishal@gmail.com");

		//send notifications
		try {
			notificationService.sendNotification(inputEmailDetails);
		}catch(MailException e) {
			//catch error
		logger.info("Error Sending Email:"+ e.getMessage());
		}
		
		return "Thank you for registering";
	}

}
