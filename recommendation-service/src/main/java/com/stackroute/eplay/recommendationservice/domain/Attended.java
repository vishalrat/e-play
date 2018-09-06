package com.stackroute.eplay.recommendationservice.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

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
@RelationshipEntity(type="ATTENDED")
public class Attended {
	@Id
	private int attendanceId;
	@StartNode
	private User user;
	@EndNode
	private TicketedEvent ticketedEvent;
}
