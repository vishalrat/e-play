package com.stackroute.eplay.showscheduler.listener;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.stackroute.eplay.showscheduler.domain.MovieEvent;
import com.stackroute.eplay.showscheduler.stream.ShowSchedulerStream;
import com.stackroute.eplay.showscheduler.trigger.ShowTrigger;

@EnableBinding(ShowSchedulerStream.class)
public class KafkaListener {

	private ShowTrigger showTrigger;

	@Autowired
	public KafkaListener(ShowTrigger showTrigger) {
		super();
		this.showTrigger = showTrigger;
	}

	@StreamListener(ShowSchedulerStream.INPUT)
	public void theatrePost(@Payload MovieEvent movieEvent) {

		try {
			showTrigger.trigger(movieEvent);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		
	}

}