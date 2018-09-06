package com.stackroute.eplay.recommendationservice.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.eplay.recommendationservice.domain.Movie;
import com.stackroute.eplay.recommendationservice.domain.TicketedEvent;

public interface TicketedEventRepository extends Neo4jRepository<TicketedEvent,Integer > {
	
	@Query("MATCH (t:TicketedEvent) WHERE t.name ={name} RETURN t")
	TicketedEvent findByName(@Param("name") String name);
	
	@Query("Match (t:TicketedEvent) WHERE t.id ={id} RETURN t")
	TicketedEvent findById(@Param("id") int id);
	
	@Query("Match (c:City) ,(t:TicketedEvent) where c.name={name} and t.id={id} with t,c merge (t)-[r:HOSTED_IN]->(c)")
	void hostedIn(@Param("name") String name,@Param("id")int id);
	
	@Query("MATCH (t:TicketedEvent)-[r:IS_OF_TYPE]->(c:Category) where c.categoryName={categoryName} RETURN t")
	List<TicketedEvent> getTicketedEventsByType(@Param("categoryName") String categoryName);
	
	@Query("MATCH (t:TicketedEvent)-[r:HOSTED_IN]->(c:City) where c.name={name} RETURN t")
	List<TicketedEvent> getTicketedEventsByCity(@Param("name") String name);
	
	@Query("MATCH (c:City)<- [:HOSTED_IN]-(t:TicketedEvent)-[:IS_OF_TYPE]->(c:Category) where c.categoryName={categoryName} and  c.name={name} RETURN t")
    List<TicketedEvent> getTicketedEventByCityType(@Param("name") String name, @Param("categoryName") String categoryName);
}
