package io.zipcoder.tc_spring_poll_application.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.zipcoder.tc_spring_poll_application.domain.Vote;


public interface VoteRepository extends CrudRepository<Vote, Long>{
    
	String query = 	"SELECT v.* " +
            		"FROM Option o, Vote v " +
            		"WHERE o.POLL_ID = ?1 " +
            		"AND v.OPTION_ID = o.OPTION_ID";
	
	@Query(value = query, nativeQuery = true)
    public Iterable<Vote> findVotesByPoll(Long pollId);
	
	
}
