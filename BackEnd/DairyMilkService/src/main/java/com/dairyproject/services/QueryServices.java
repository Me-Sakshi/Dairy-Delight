package com.dairyproject.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyproject.dto.ConsumerQueryMessage;
import com.dairyproject.entities.ConsumerDetails;
import com.dairyproject.entities.ConsumerQuery;
import com.dairyproject.entities.SellerDetails;
import com.dairyproject.entities.SellerQuery;
import com.dairyproject.repositories.ConsumerQueryRepository;
import com.dairyproject.repositories.ConsumerRepository;
import com.dairyproject.repositories.SellerQueryRepository;
import com.dairyproject.repositories.SellerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class QueryServices {

	@Autowired
	private ConsumerQueryRepository conQueryRepo;

	@Autowired
	private SellerQueryRepository sellQueryRepo;

	@Autowired
	private ConsumerRepository conRepo;

	@Autowired
	private SellerRepository sellRepo;

	//@Autowired
	private ConsumerDetails conDetails;

	//@Autowired
	private SellerDetails sellDetails;

	// Consumer Query

	public String insertNewConsumerQuery(ConsumerQueryMessage consumerQuery) {
		ConsumerQuery conQuery = new ConsumerQuery();
		conDetails = conRepo.findConsumerDetailsByEmailIdOnly(consumerQuery.getEmailId());
		System.out.println(consumerQuery.getEmailId());
		if (conDetails != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
			String dateTime = dtf.format(LocalDateTime.now());
			conQuery.setConsumerDetails(conDetails);
			conQuery.setDateTime(dateTime);
			conQuery.setMessage(consumerQuery.getMessage());
			conQueryRepo.save(conQuery);
			conDetails = null;
			return "Thank you for your valuable time. We will soon get back to you with proper solution !";
		}

		return "Query insertion failed ! Please try after some time. Sorry for the inconvenience...";
	}

	public List<ConsumerQuery> getAllConsumerQueries() {
		return conQueryRepo.findAllConsumerQueries();
	}

	public List<ConsumerQuery> getConsumerQueriesByDateTime(String dateTime) {
		return conQueryRepo.findConsumerQueriesByDateTime(dateTime);
	}

	public List<ConsumerQuery> getConsumerQueriesByConsumerEmailId(String emailId) {
		return conQueryRepo.findConsumerQueriesByConsumerEmailId(emailId);
	}

	// Seller Query

	public String insertNewSellerQuery(ConsumerQueryMessage sellerQuery) {
		SellerQuery sellQuery = new SellerQuery();
		sellDetails = sellRepo.findSellerDetailsByEmailIdOnly(sellerQuery.getEmailId());
		if (sellDetails != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
			String dateTime = dtf.format(LocalDateTime.now());
			sellQuery.setSellerDetails(sellDetails);
			sellQuery.setDateTime(dateTime);
			sellQuery.setMessage(sellerQuery.getMessage());
			sellQueryRepo.save(sellQuery);
			sellDetails = null;
			return "Thank you for your valuable time. We will soon get back to you with proper solution !";
		}

		return "Query insertion failed ! Please try after some time. Sorry for the inconvenience...";
	}

	public List<SellerQuery> getAllSellerQueries() {
		return sellQueryRepo.findAllSellerQueries();
	}

	public List<SellerQuery> getSellerQueriesByDateTime(String dateTime) {
		return sellQueryRepo.findSellerQueriesByDateTime(dateTime);
	}

	public List<SellerQuery> getSellerQueriesBySellerEmailId(String emailId) {
		return sellQueryRepo.findSellerQueriesBySellerEmailId(emailId);
	}

}
