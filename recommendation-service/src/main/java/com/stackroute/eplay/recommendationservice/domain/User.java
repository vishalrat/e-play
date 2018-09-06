package com.stackroute.eplay.recommendationservice.domain;

import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class User {
	
	@Id
	private String userName;
	//private String fullName;
	
	@Relationship(type="LIVES_IN",direction=Relationship.OUTGOING)
	private City city ;
	
	@Relationship(type = "VIEWED", direction = Relationship.OUTGOING)
	private List<Movie> movies;
	
	@Relationship(type = "ATTENDED", direction = Relationship.OUTGOING)
	private List<TicketedEvent> ticketedEvent;
	
	public User(String userName, City city) {
		this.userName = userName;
		//this.fullName = fullName;
		this.city = city;
	}
//	@Relationship(type="FOLLOWS",direction = Relationship.OUTGOING)
//	private List<User> users;
//	
//	@Relationship(type="FOLLOWED_BY",direction = Relationship.INCOMING)
//	private List<User> otherUsers;

	
}
