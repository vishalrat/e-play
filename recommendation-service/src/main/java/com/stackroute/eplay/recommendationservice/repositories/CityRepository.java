package com.stackroute.eplay.recommendationservice.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.eplay.recommendationservice.domain.City;

public interface CityRepository extends Neo4jRepository<City,String> {
	
	@Query("MATCH (c:City) WHERE c.name ={name} RETURN c")
	City findBycityName(@Param("name") String name); 
}
