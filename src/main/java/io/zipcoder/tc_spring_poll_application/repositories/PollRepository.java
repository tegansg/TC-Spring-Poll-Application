package io.zipcoder.tc_spring_poll_application.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.zipcoder.tc_spring_poll_application.domain.Option;
import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.domain.Vote;

public interface PollRepository extends CrudRepository<Poll, Long>{

	
}
