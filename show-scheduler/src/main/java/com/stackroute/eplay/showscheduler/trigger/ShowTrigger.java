package com.stackroute.eplay.showscheduler.trigger;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.showscheduler.domain.MovieEvent;
import com.stackroute.eplay.showscheduler.domain.Show;
import com.stackroute.eplay.showscheduler.job.ShowJob;

/*
 * Trigger class for Show Scheduling
 */

@Service
public class ShowTrigger {

	Map<Integer, Show> showMap;

	public ShowTrigger() {
		showMap = new HashMap<Integer, Show>();
	}

	public void trigger(MovieEvent movieEvent) throws SchedulerException {

		/*
		 * Getting all the shows from the movie event and storing all the shows in the
		 * list
		 */

		List<Show> shows = movieEvent.getShows();

		Scheduler sc = StdSchedulerFactory.getDefaultScheduler();

		for (Show show : shows) {
			System.out.println("shows: " + show);

			/*
			 * Creating a new job for every show with the job key as the show id
			 */

			JobKey jobKey = JobKey.jobKey("job" + Integer.toString(show.getShowId()));
			JobDetail job = JobBuilder.newJob(ShowJob.class).withIdentity(jobKey).build();

			/*
			 * taking current time in milliseconds
			 */

			long currentTimeInMilliSeconds = System.currentTimeMillis();

			/*
			 * taking show time in milliseconds
			 */

			LocalTime oldShowTime = show.getStartTime();
			LocalDate showDate = show.getDate();
			LocalTime showTime=oldShowTime.minusMinutes(330);
			LocalTime time = LocalTime.parse("05:30", DateTimeFormatter.ofPattern("HH:mm"));
			if(oldShowTime.compareTo(time)<=0) {
				showDate = showDate.minusDays(1);
			}
			LocalDateTime showDateTime = LocalDateTime.of(showDate, showTime);
			long showTimeInMilliSeconds = Timestamp.valueOf(showDateTime).getTime();

			/*
			 * calculating the difference between the current time and the show time so that
			 * the job can be scheduled to run after that much time
			 */

			long secondsDiff = (showTimeInMilliSeconds - currentTimeInMilliSeconds) / 1000;

			System.out.println("time diff: " + (int) secondsDiff);

			/*
			 * creating a trigger for each show with the trigger name as the show id
			 */

			TriggerKey triggerKey = TriggerKey.triggerKey("trigger" + Integer.toString(show.getShowId()));
			SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
					.startAt(futureDate((int) secondsDiff, IntervalUnit.SECOND)).forJob(job)
					.withSchedule(simpleSchedule()).build();

			showMap.put(show.getShowId(), show);

			/*
			 * passing all the shows in the job class and scheduling the job
			 */

			sc.getContext().put("show", showMap);
			sc.scheduleJob(job, trigger);

		}
		sc.start();

	}

}