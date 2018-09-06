package com.stackroute.eplay.recommendationservice.domain;

import java.util.List;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Genre {
	@Id
	private String genreName;
	
//	@Relationship(type = "IS_OF_GENRE", direction = Relationship.INCOMING)
//	private List<Movie> movies;

	public Genre() {
		
	}

	public Genre( String genreName) {
		this.genreName = genreName;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
}
