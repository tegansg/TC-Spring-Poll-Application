package io.zipcoder.tc_spring_poll_application.controller;

import java.util.Collection;
import java.util.HashMap;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dtos.OptionCount;
import dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.domain.Option;
import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;

@RestController
public class ComputeResultController {
	
	@Inject
    private VoteRepository voteRepository;
	    
    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();

		Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);
		HashMap<Long, OptionCount> results = new HashMap<>();
		int totalVotes = 0;
		
		for (Vote vote : allVotes) {

			Option option = vote.getOption(); //
			Long optionId = option.getId();
		
        	if(!results.containsKey(optionId))
        	{
        		OptionCount oCount = new OptionCount();
				oCount.setCount(1);
				oCount.setOptionId(optionId);
				results.put(optionId, oCount);
        	}
        	else
        	{
        		OptionCount oCount = results.get(optionId);
        		oCount.incrementCount();
        		results.put(optionId, oCount);
        	}
			totalVotes+=1;

		}
		
		Collection<OptionCount> finalResults = results.values();
		voteResult.setResults(finalResults);
        voteResult.setTotalVotes(totalVotes);
 
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
    
}

    
    