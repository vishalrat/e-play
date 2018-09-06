package com.stackroute.eplay.userregistration.domain;

public class Token {

	
	private String username;
	private String token;
	
	public Token() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Token(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}
	
}
