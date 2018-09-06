package com.stackroute.eplay.ticketengine.service;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.ticketengine.domain.BlockedSeats;
import com.stackroute.eplay.ticketengine.domain.Show;
import com.stackroute.eplay.ticketengine.repository.ShowRepository;

@Service
public class SeatsJob implements Job {

	private static BlockedSeatsService blockedSeatsService;
	private static ShowRepository showRepository;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public SeatsJob() {
	}

	@Autowired
	public SeatsJob(BlockedSeatsService blockedSeatsService, ShowRepository showRepository) {
		if (SeatsJob.blockedSeatsService == null)
			SeatsJob.blockedSeatsService = blockedSeatsService;
		if (SeatsJob.showRepository == null)
			SeatsJob.showRepository = showRepository;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String id = context.getTrigger().getJobKey().toString().replace("DEFAULT.", "");
		logger.info("id: " + id);
		if (blockedSeatsService.findById(id) != null && blockedSeatsService.findById(id).isPresent()) {
			BlockedSeats seats = blockedSeatsService.findById(id).get();
			blockedSeatsService.delete(id);
			Show show = showRepository.find(seats.getShowId());
			Map<Integer, String> showSeats = show.getSeats();
			for (int i : seats.getSeats()) {
				if (showSeats.get(i).equals("blocked")) {
					showSeats.put(i, "open");
				}
			}
			show.setSeats(showSeats);
			showRepository.update(show);
			seats.setStatus("open");
			CommService.send(seats);
		}

	}

}
