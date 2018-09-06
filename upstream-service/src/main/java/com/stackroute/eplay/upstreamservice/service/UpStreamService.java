package com.stackroute.eplay.upstreamservice.service;

import com.stackroute.eplay.upstreamservice.domain.Movie;
import com.stackroute.eplay.upstreamservice.domain.MovieEvent;
import com.stackroute.eplay.upstreamservice.domain.TicketedEvent;
import com.stackroute.eplay.upstreamservice.domain.RSVPEvent;
import com.stackroute.eplay.upstreamservice.domain.Show;
import com.stackroute.eplay.upstreamservice.domain.Theatre;

public interface UpStreamService {

	public void postMovieEvent(MovieEvent event);
	public void postTicketedEvent(TicketedEvent event);
	public void postRSVPEvent(RSVPEvent event);
	public void postMovie(Movie event);
	public void postTheatre(Theatre event);
	public void postShow(Show show);
}
