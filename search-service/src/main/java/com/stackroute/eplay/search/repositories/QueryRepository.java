package com.stackroute.eplay.search.repositories;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.search.domain.Query;

@Repository
public interface QueryRepository extends MongoRepository<Query, Integer> {

}
