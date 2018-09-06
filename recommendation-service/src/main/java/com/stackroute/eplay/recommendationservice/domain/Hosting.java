package com.stackroute.eplay.recommendationservice.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="HOSTED_IN")
public class Hosting {
	@StartNode
	TicketedEvent ticketedevent;
	@EndNode
	City city;
}
