package com.stackroute.eplay.ticketengine.service;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.util.Optional;

import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.ticketengine.domain.BlockedSeats;
import com.stackroute.eplay.ticketengine.domain.Show;
import com.stackroute.eplay.ticketengine.repository.BlockedSeatsRepository;
import com.stackroute.eplay.ticketengine.repository.ShowRepository;

@Service
public class BlockedSeatsServiceImpl implements BlockedSeatsService {

	private BlockedSeatsRepository blockedSeatsRepository;
	private ShowRepository showRepository;

	@Autowired
	public BlockedSeatsServiceImpl(BlockedSeatsRepository blockedSeatsRepository, ShowRepository showRepository) {
		this.blockedSeatsRepository = blockedSeatsRepository;
		this.showRepository = showRepository;
	}

	@Override
	public BlockedSeats save(BlockedSeats blockedSeats) throws Exception {
		blockedSeats = blockedSeatsRepository.save(blockedSeats);
		Show show = showRepository.find(blockedSeats.getShowId());
		for (int i : blockedSeats.getSeats()) {
			if (show.getSeats().get(i).equals("blocked")) {
				throw new Exception("Seat No: " + i + " is already blocked");
			} else if (show.getSeats().get(i).equals("booked")) {
				throw new Exception("Seat No: " + i + " is already booked");
			} else {
				show.getSeats().put(i, "blocked");
			}
		}
		showRepository.save(show);

		JobKey jobKey = JobKey.jobKey(blockedSeats.getId());
		JobDetail job = JobBuilder.newJob(SeatsJob.class).withIdentity(jobKey).build();
		TriggerKey triggerKey = TriggerKey.triggerKey(blockedSeats.getId());
		SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
				.startAt(futureDate(60, IntervalUnit.SECOND)).forJob(job).withSchedule(simpleSchedule()).build();
		Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
		sc.getContext().put("seat", blockedSeats.getId());
		sc.scheduleJob(job, trigger);
		sc.start();

		return blockedSeats;
	}

	@Override
	public Optional<BlockedSeats> findById(String id) {
		return blockedSeatsRepository.findById(id);
	}

	@Override
	public Iterable<BlockedSeats> getAll() {
		return blockedSeatsRepository.findAll();
	}

	@Override
	public void delete(String id) {
		blockedSeatsRepository.deleteById(id);

	}

	@Override
	public BlockedSeats update(BlockedSeats blockedSeats) throws Exception {
		return save(blockedSeats);
	}

	@Override
	public void deleteAll() {
		blockedSeatsRepository.deleteAll();
	}

}
