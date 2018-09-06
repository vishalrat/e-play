package com.stackroute.eplay.search.domain;

import java.io.File;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Theatre {

	@Id
	private int theatreId;
	private String theatreName;
	private String theatreLocation;
	private int capacity;
	private File arenaFile;
	private String userName;
	private List<Show> shows;
}
