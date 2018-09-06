package com.stackroute.eplay.recommendationservice.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="RELEASED_IN")
public class Release {
	@StartNode
	Movie movie;
	@EndNode
	City city;
}
