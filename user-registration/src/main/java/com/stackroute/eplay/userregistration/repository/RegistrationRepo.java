package com.stackroute.eplay.userregistration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.userregistration.domain.Registration;

@Repository
public interface RegistrationRepo extends MongoRepository<Registration, String> {

	public Registration findByUserName(String userName);

	public Registration getUserByEmail(String email);
}
