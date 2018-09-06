package com.stackroute.eplay.ticketengine.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.stackroute.eplay.ticketengine.domain.MovieEvent;
import com.stackroute.eplay.ticketengine.domain.Show;
import com.stackroute.eplay.ticketengine.repository.ShowRepository;
import com.stackroute.eplay.ticketengine.streams.FinalMovieEventStream;
import com.stackroute.eplay.ticketengine.streams.MovieEventStream;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@EnableBinding({ MovieEventStream.class, FinalMovieEventStream.class })
public class KafkaListener {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ShowRepository showRepository;
	
	@Autowired
	KafkaListener(ShowRepository showRepository){
		this.showRepository = showRepository;
	}
	
	@StreamListener(MovieEventStream.INPUT)
	public void movieEventPost(@Payload MovieEvent event) {
		for(Show show: event.getShows()) {
			if(show.getStatus())
				showRepository.save(show);
			else {
				if(showRepository.find(show.getShowId())!=null)
					showRepository.delete(show.getShowId());
			}
		}
		logger.info(event.toString() + " movie");
	}
	
	@StreamListener(FinalMovieEventStream.INPUT)
	public void finalMovieEventPost(@Payload MovieEvent event) {
		for(Show show: event.getShows()) {
			if(show.getStatus())
				showRepository.save(show);
			else {
				if(showRepository.find(show.getShowId())!=null)
					showRepository.delete(show.getShowId());
			}
		}
		logger.info(event.toString() + " movie");
	}
}