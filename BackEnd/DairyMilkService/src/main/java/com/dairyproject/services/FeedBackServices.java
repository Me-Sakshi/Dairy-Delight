package com.dairyproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyproject.entities.FeedBackDetails;
import com.dairyproject.repositories.FeedBackRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FeedBackServices {

	@Autowired
	private FeedBackRepository feedRepo;

	public List<FeedBackDetails> getAllFeedBackDetails() {
		return feedRepo.findAllFeedBackDetails();
	}

	public List<FeedBackDetails> getFeedBackDetailsByname(String name) {
		return feedRepo.findFeedBackDetailsByName(name);
	}

	public List<FeedBackDetails> getFeedBackDetailsBySubject(String subject) {
		return feedRepo.findFeedBackDetailsBySubject(subject);
	}

	public FeedBackDetails getFeedBackDetailsById(Integer fid) {
		return feedRepo.findFeedBackDetailsById(fid);
	}

	public String insertNewFeedBackDetails(FeedBackDetails feedBackDetails) {
		if (feedRepo.save(feedBackDetails) != null) {
			return "Thank You for your valuable feedback !";
		}
		return "Failed to submit your feedback ! Please try again later.";
	}

}
