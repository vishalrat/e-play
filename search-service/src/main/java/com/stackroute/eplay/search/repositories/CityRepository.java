package com.stackroute.eplay.search.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.search.domain.City;

@Repository
public interface CityRepository extends MongoRepository<City, String> {
//    public Iterable<Movie> getEventsByCity(@Param("cityName") String cityName);
	
}
