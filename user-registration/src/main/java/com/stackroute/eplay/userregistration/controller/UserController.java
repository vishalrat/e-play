package com.stackroute.eplay.userregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.eplay.userregistration.domain.Registration;
import com.stackroute.eplay.userregistration.domain.Theatre;
import com.stackroute.eplay.userregistration.exception.EmailAlreadyExistsException;
import com.stackroute.eplay.userregistration.exception.UserAlreadyExistsException;
import com.stackroute.eplay.userregistration.exception.UserNameAlreadyExistsException;
import com.stackroute.eplay.userregistration.service.RegisterUser;
import com.stackroute.eplay.userregistration.stream.UserRegistrationStream;

/*
 * Controller class for User Registration
 */

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@EnableBinding(UserRegistrationStream.class)
public class UserController {

	private RegisterUser registerUser;
	private UserRegistrationStream userRegistrationStream;

	@Autowired
	public UserController(RegisterUser registerUser, UserRegistrationStream userRegistrationStream) {
		this.registerUser = registerUser;
		this.userRegistrationStream = userRegistrationStream;
		// this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@RequestMapping("/")
	public String hello() {
		return "In Registration conotroller";
	}

	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody Registration registrant) throws UserAlreadyExistsException {
		try {
			registerUser.addUser(registrant);
		
			// kafkaTemplate.send(TOPIC , registrant);
			 MessageChannel messageChannel = userRegistrationStream.outboundUserRegistration();
		     messageChannel.send(MessageBuilder
		                .withPayload(registrant)
		                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
		                .build());
			return new ResponseEntity<String>("New User Added", HttpStatus.CREATED);
		}
		 catch(UserAlreadyExistsException e){
    		// logger.error("This is an MovieAlreadyExistsException error");
             return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
         }
	}
	
	@GetMapping("/login/getAllDetais/{userName}")
	public ResponseEntity<?> getAllDetailsByUsername(@PathVariable String username){
		return  new ResponseEntity<Registration>(registerUser.findByUsername(username), HttpStatus.OK);

	}
	

	@GetMapping("/register/check/userName/{userName}")
	public boolean checkUserName(@PathVariable("userName") String userName) {
		try {
			registerUser.checkForUserName(userName);
			return false;
		} catch (UserNameAlreadyExistsException e) {
			return true;
		}
	}

	@GetMapping("/register/check/email/{email}")
	public boolean checkEmail(@PathVariable("email") String email) {
		try {
			registerUser.checkForEmail(email);
			return false;
		} catch (EmailAlreadyExistsException e) {
			return true;
		}
	}
	@GetMapping("/user/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
      //  try {
            return new ResponseEntity<Registration>(registerUser.findByUsername(username), HttpStatus.OK);
//        } catch (UserNotFoundException e) {
//            return new ResponseEntity<String>("User Not Found", HttpStatus.UNAUTHORIZED);
//        }
    }
	@PostMapping("/registerTheatre")
	public ResponseEntity<?> saveTheatre(@RequestBody Theatre theatre) {
		registerUser.saveTheatre(theatre);
		// kafkaTemplate.send(TOPIC , registrant);
		return new ResponseEntity<Registration>(HttpStatus.CREATED);
	}
}
