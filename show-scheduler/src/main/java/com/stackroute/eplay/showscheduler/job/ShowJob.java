package com.stackroute.eplay.showscheduler.job;

import java.util.HashMap;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.stackroute.eplay.showscheduler.domain.Show;
import com.stackroute.eplay.showscheduler.stream.ShowSchedulerStream;

/*
 * Job class for Show Scheduling 
 */

@Service
@SuppressWarnings("unchecked")
public class ShowJob implements Job {

	static MessageChannel messageChannel;
	static ShowSchedulerStream showSchedulerStream;

	public ShowJob() {

	}

	@Autowired
	public ShowJob(ShowSchedulerStream showSchedulerStream) {
		if (ShowJob.showSchedulerStream == null) {
			ShowJob.showSchedulerStream = showSchedulerStream;
		}
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		SchedulerContext schedulerContext;

		try {

			/*
			 * Getting all the shows from the trigger class in the form of Hashmap and
			 * changing the status of the each show as soon as their time is triggered.
			 */

			schedulerContext = context.getScheduler().getContext();

			HashMap<Integer, Show> showMap = (HashMap<Integer, Show>) schedulerContext.get("show");

			String[] trigger = context.getTrigger().toString().split(":");
			int beginIndex = trigger[0].indexOf("trigger") + 7;
			int endIndex = trigger[0].length() - 1;
			int index = Integer.parseInt(trigger[0].substring(beginIndex, endIndex));
			System.out.println("index: " + index);
			Show show = showMap.get(index);
			System.out.println("For Show: " + show);
			System.out.println("Before: " + show.isStatus());
			show.setStatus(false);
			System.out.println("After: " + show.isStatus());

			messageChannel = showSchedulerStream.outboundShowScheduler();
			messageChannel.send(MessageBuilder.withPayload(show)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}