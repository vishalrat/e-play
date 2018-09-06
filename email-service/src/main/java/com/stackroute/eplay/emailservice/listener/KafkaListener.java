package com.stackroute.eplay.emailservice.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.mail.MailException;
import org.springframework.messaging.handler.annotation.Payload;

import com.stackroute.eplay.emailservice.domain.InputEmailDetails;
import com.stackroute.eplay.emailservice.service.EmailNotificationService;
import com.stackroute.eplay.emailservice.streams.EmailStream;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@EnableBinding({ EmailStream.class })
public class KafkaListener {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public EmailNotificationService notificationService;
	
	@Autowired
	KafkaListener(EmailNotificationService notificationService){
		this.notificationService = notificationService;
	}
	
	@StreamListener(EmailStream.INPUT)
	public void sendEmail(@Payload InputEmailDetails email) {
		try {
			notificationService.sendNotification(email);
		}catch(Exception e) {
			//catch error
		logger.info("Error Sending Email:"+ e.getMessage());
		}
		logger.info(email.toString() + " email");
	}
}