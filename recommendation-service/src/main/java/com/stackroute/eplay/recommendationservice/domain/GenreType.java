package com.stackroute.eplay.recommendationservice.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="IS_OF_GENRE")
public class GenreType {
	
	@StartNode
	Movie movie;
	
	@EndNode
	Genre genre;
}
