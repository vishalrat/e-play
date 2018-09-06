package com.stackroute.eplay.userregistration.service;

import com.stackroute.eplay.userregistration.domain.Registration;
import com.stackroute.eplay.userregistration.domain.Theatre;
import com.stackroute.eplay.userregistration.exception.EmailAlreadyExistsException;
import com.stackroute.eplay.userregistration.exception.UserAlreadyExistsException;
import com.stackroute.eplay.userregistration.exception.UserNameAlreadyExistsException;

public interface RegisterUser {

	public Registration checkForUserName(String userName) throws UserNameAlreadyExistsException;

	public Registration checkForEmail(String email) throws EmailAlreadyExistsException;

	public Registration addUser(Registration registrant) throws  UserAlreadyExistsException;

	public Registration findByUsername(String userName);
	
	public Registration updateUser(Registration user, String username);
	
	public Registration saveTheatre(Theatre theatre);

}
