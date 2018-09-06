package com.stackroute.eplay.recommendationservice.domain;

import java.util.Date;

import java.util.List;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class TicketedEvent {
	
	@Id
	private int id;
	private String name;
	private Date date;
	private String backGroundPoster;
	private String cardPoster;
	
	
	public TicketedEvent(int id, String name, Date date, City city, Category category, String backGroundPoster, String cardPoster) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.city = city;
		this.category= category;
		this.cardPoster=cardPoster;
		this.backGroundPoster= backGroundPoster;
	}
	
	@Relationship(type="HOSTED_IN",direction=Relationship.OUTGOING)
	private City city ;
	
	@Relationship(type="IS_OF_TYPE",direction=Relationship.OUTGOING)
	private Category category;
	
//	@JsonIgnoreProperties("ticketedEvent")
	@Relationship(type="ATTENDED",direction=Relationship.INCOMING)
	private List<User> user;
}
