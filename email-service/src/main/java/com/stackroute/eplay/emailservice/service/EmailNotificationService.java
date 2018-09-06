package com.stackroute.eplay.emailservice.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.emailservice.domain.InputEmailDetails;

@Service
public class EmailNotificationService {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailNotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}
	
	public void sendNotification(InputEmailDetails inputEmailDetails) throws AddressException, MessagingException {
		
		//send email
		SimpleMailMessage mail= new SimpleMailMessage();
//		Properties properties = new Properties();
//		Session session = Session.getInstance(properties, null);
//		MimeMessage mail= new MimeMessage(session);

		
//		String email1="aerospacevishal@gmail.com";
//		String email2="skumarvit3@gmail.com";
//		String email3="manutyagi4@gmail.com";
//		List<String> emailList=new ArrayList<String>();
//		emailList.add(email2);
//		emailList.add(email1);
//		emailList.add(email3);
		
//		Address[] cc = new Address[] {InternetAddress.parse("abc@abc.com"),
//                InternetAddress.parse("abc@def.com"), 
//                InternetAddress.parse("ghi@abc.com")};
//		
//		mail.addRecipient(Message.RecipientType.TO,   
//				new InternetAddress("skumarvit3@gmail.com"));  
		//mail.addRecipients(Message.RecipientType.CC, 
		//		InternetAddress.parse("skumarvit3@gmail.com,aerospacevishal@gmail.com,manutyagi4@gmail.com"));
		mail.setTo(inputEmailDetails.getEmailAddress());
		mail.setBcc(inputEmailDetails.getEmailBcc()); 	// email id of multiple recipients using Bcc.
	//	mail.setFrom("skumarvit3@gmail.com");
//		mail.setHeader("Hi..", "qwerty");
		mail.setSubject(inputEmailDetails.getSubject()); 	//Subject for event.
		mail.setText(inputEmailDetails.getBody());	// 
//		mail.addRecipient(Message.RecipientType.TO, InternetAddress.parse("skumarvit3@gmail.com"));
		javaMailSender.send(mail);
//		Transport.send(mail);
	}

}