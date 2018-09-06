package com.stackroute.eplay.userregistration.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

import com.stackroute.eplay.userregistration.domain.InputEmailDetails;
import com.stackroute.eplay.userregistration.domain.Registration;
import com.stackroute.eplay.userregistration.domain.Theatre;
import com.stackroute.eplay.userregistration.exception.EmailAlreadyExistsException;
import com.stackroute.eplay.userregistration.exception.UserAlreadyExistsException;
import com.stackroute.eplay.userregistration.exception.UserNameAlreadyExistsException;
import com.stackroute.eplay.userregistration.repository.RegistrationRepo;
import com.stackroute.eplay.userregistration.stream.EmailStream;


@Service
@EnableBinding(EmailStream.class)
public class RegisterUserImpl implements RegisterUser {

	private RegistrationRepo registrationRepo; 
	private EmailStream emailStream;
	
	@Autowired
	public RegisterUserImpl(RegistrationRepo registrationRepo, EmailStream emailStream) {
		this.registrationRepo = registrationRepo;
		this.emailStream = emailStream;
	}

	@Autowired
	NextSequenceService nextSequenceService;
	@Override
	public Registration addUser(Registration registrant) throws UserAlreadyExistsException {
		Iterable<Registration> movies = getAllRegisterUser();
		Iterator<Registration> iterator = movies.iterator();
		
		// movie.setId(nextSequenceService.getNextSequence("counter"));
		while (iterator.hasNext()) {
			Registration oldUser = iterator.next();
			if (registrant.getUserName().equals(oldUser.getUserName())) {
				throw new UserAlreadyExistsException("User already exists");
			}
		}
		
		InputEmailDetails email= new InputEmailDetails();
		email.setEmailAddress(registrant.getEmail());
		email.setSubject("Registration Confirmation");
		email.setBody("Thanks for registering.\nNow you can create events and see recommendations based on ur previous booking.");
		MessageChannel messageChannelEmail = emailStream.outboundEmail();
		messageChannelEmail.send(MessageBuilder.withPayload(email)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
		return registrationRepo.save(registrant);
	}
	public Iterable<Registration> getAllRegisterUser() {

		return registrationRepo.findAll();
	}
	@Override
	public Registration checkForUserName(String userName) throws UserNameAlreadyExistsException {
		Registration registrant = registrationRepo.findByUserName(userName);
		if (registrant == null)
			return registrant;
		else
			throw new UserNameAlreadyExistsException("User name already present");
	}

	@Override
	public Registration checkForEmail(String email) throws EmailAlreadyExistsException {
		Registration registrant = registrationRepo.getUserByEmail(email);
		if (registrant == null)
			return registrant;
		else
			throw new EmailAlreadyExistsException("Email already present");

	}

	@Override
	public Registration findByUsername(String userName) {
		Registration registrant = registrationRepo.findByUserName(userName);
		if (registrant == null)
			return null;
		else
			return registrant;
	}
	
	public Registration saveTheatre(Theatre theatre) {
		Registration user=findByUsername(theatre.getUserName());
		List<Theatre> theatres=user.getTheatres();
		if(theatres==null) {
			theatres=new ArrayList<Theatre>();
		}
		theatre.setTheatreId(nextSequenceService.getNextSequence("counter"));
		theatres.add(theatre);
		user.setTheatres(theatres);
		registrationRepo.save(user);
		return user;
	}
	@Override
	public Registration updateUser(Registration user, String username) {
		user.setUserName(username);
		return registrationRepo.save(user);
	}


}
