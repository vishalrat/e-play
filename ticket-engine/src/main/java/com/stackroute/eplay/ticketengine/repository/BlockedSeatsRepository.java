package com.stackroute.eplay.ticketengine.repository;

import org.springframework.data.repository.CrudRepository;

import com.stackroute.eplay.ticketengine.domain.BlockedSeats;

public interface BlockedSeatsRepository extends CrudRepository<BlockedSeats, String>{

}
