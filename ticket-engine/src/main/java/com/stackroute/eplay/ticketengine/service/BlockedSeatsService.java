package com.stackroute.eplay.ticketengine.service;

import java.util.Optional;

import com.stackroute.eplay.ticketengine.domain.BlockedSeats;

public interface BlockedSeatsService {
	public BlockedSeats save(BlockedSeats blockedSeats) throws Exception;
	public Optional<BlockedSeats> findById(String id);
	public Iterable<BlockedSeats> getAll();
	public void delete(String id);
	public void deleteAll();
	public BlockedSeats update(BlockedSeats blockedSeats) throws Exception;
}
