package com.stackroute.eplay.ticketengine.repository;

import java.util.Map;

import com.stackroute.eplay.ticketengine.domain.Show;

public interface ShowRepository {
	
	public void save(Show show);

	public Show find(Long id);

	public Map<Long, Show> findAll();

	public void update(Show show);

	public void delete(Long id);
}
