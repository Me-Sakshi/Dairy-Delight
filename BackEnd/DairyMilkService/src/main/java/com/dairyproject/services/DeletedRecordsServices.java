package com.dairyproject.services;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyproject.dto.Login;
import com.dairyproject.entities.ConsumerDetails;
import com.dairyproject.entities.DeletedConsumerRecords;
import com.dairyproject.entities.DeletedSellerRecords;
import com.dairyproject.entities.SellerDetails;
import com.dairyproject.repositories.ConsumerRepository;
import com.dairyproject.repositories.DeletedConsumerRepository;
import com.dairyproject.repositories.DeletedSellerRepository;
import com.dairyproject.repositories.SellerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeletedRecordsServices {

	//@Autowired
	private ConsumerDetails consumerDetails;

	@Autowired
	private ConsumerRepository conRepo;

//	@Autowired
//	private DeletedConsumerRecords delConRecord;

	@Autowired
	private DeletedConsumerRepository delConsumerRepo;

	//@Autowired
	private SellerDetails sellDetails;

	@Autowired
	private SellerRepository sellRepo;

//	@Autowired
//	private DeletedSellerRecords delSellRecord;

	@Autowired
	private DeletedSellerRepository delSellRepo;

	DeletedConsumerRecords delConRecord = new DeletedConsumerRecords();
	DeletedSellerRecords delSellRecord = new DeletedSellerRecords();

	/**
	 *
	 * 	Entity
	 * 		Transient  -> new
	 * 		persistent/managed  -persist/save
	 * 		removed  - remove
	 * 		detached -> merge()
	 *
	 * 		Hibernate
	 * 				-> find()  Persistent Context(L1 cache)
	 *
	 *
	 * */

	public String deleteConsumerByEmailId(Login login) throws UnsupportedEncodingException {
		String encryptedPassword = Base64.getEncoder().encodeToString(login.getPassword().getBytes("UTF-8"));
		consumerDetails = conRepo.findConsumerDetailsByEmailAndPassword(login.getEmailId(), encryptedPassword);
		if (consumerDetails != null && conRepo.deleteConsumerDetailsByEmailId(consumerDetails.getEmailId()) == 1) {
			delConRecord.setAddress(consumerDetails.getAddress());
			delConRecord.setEmailId(consumerDetails.getEmailId());
			delConRecord.setFirstName(consumerDetails.getFirstName());
			delConRecord.setLastName(consumerDetails.getLastName());
			delConRecord.setGender(consumerDetails.getGender());
			delConRecord.setPhoneNumber(consumerDetails.getPhoneNumber());
			delConRecord.setConsumerId(consumerDetails.getConsumerId());
			delConRecord.setStreet(consumerDetails.getStreet());
			delConRecord.setUsername(consumerDetails.getUsername());
			delConsumerRepo.save(delConRecord);
			consumerDetails = null;
			delConRecord = null;
			return "Consumer account removed !";
		}

		return "Account not found !";

	}

	public String deleteConsumerByConsumerId(Integer consumerId) {
//		DeletedConsumerRecords delConRecord = new DeletedConsumerRecords();
		consumerDetails = conRepo.findConsumerDetailsByConsumerId(consumerId);
		if (consumerDetails != null && conRepo.deleteConsumerDetailsByConsumerId(consumerDetails.getConsumerId()) == 1) {
			delConRecord.setAddress(consumerDetails.getAddress());
			delConRecord.setEmailId(consumerDetails.getEmailId());
			delConRecord.setFirstName(consumerDetails.getFirstName());
			delConRecord.setLastName(consumerDetails.getLastName());
			delConRecord.setGender(consumerDetails.getGender());
			delConRecord.setPhoneNumber(consumerDetails.getPhoneNumber());
			delConRecord.setConsumerId(consumerDetails.getConsumerId());
			delConRecord.setStreet(consumerDetails.getStreet());
			delConRecord.setUsername(consumerDetails.getUsername());
			delConsumerRepo.save(delConRecord);
			consumerDetails = null;
			delConRecord = null;
			return "Consumer account removed !";
		}
		return "Account not found !";

	}

	public List<DeletedConsumerRecords> getDeletedAllConsumerRecords() {
		return delConsumerRepo.findAllDeletedConsumerRecords();
	}

	public List<DeletedSellerRecords> getDeletedAllSellerRecords() {
		return delSellRepo.findAllDeletedSellerRecords();
	}

	public DeletedConsumerRecords getDeletedConsumerRecordByEmailId(String emailId) {
		return delConsumerRepo.findDeletedConsumerByEmailId(emailId);
	}

	public List<DeletedConsumerRecords> getDeletedConsumerRecordsByFirstName(String name) {
		return delConsumerRepo.findDeletedConsumerRecordsByFirstName(name);
	}

	
	//seller section
	public String deleteSellerByEmailId(Login login) throws UnsupportedEncodingException {
		String encryptedPassword = Base64.getEncoder().encodeToString(login.getPassword().getBytes("UTF-8"));
		sellDetails = sellRepo.findSellerDetailsByEmailAndPassword(login.getEmailId(), encryptedPassword);
		if (sellDetails != null && sellRepo.deleteSellerDetailsByEmailId(sellDetails.getEmailId()) == 1) {
			delSellRecord.setAddress(sellDetails.getAddress());
			delSellRecord.setEmailId(sellDetails.getEmailId());
			delSellRecord.setFirstName(sellDetails.getFirstName());
			delSellRecord.setLastName(sellDetails.getLastName());
			delSellRecord.setGender(sellDetails.getGender());
			delSellRecord.setPhoneNumber(sellDetails.getPhoneNumber());
			delSellRecord.setSellerId(sellDetails.getSellerId());
			delSellRecord.setStreet(sellDetails.getStreet());
			delSellRecord.setUsername(sellDetails.getUsername());
			delSellRepo.save(delSellRecord);
			sellDetails = null;
			return "Consumer account removed !";
		}

		return "Account not found !";

	}

	public String deleteSellerrBySellerId(Integer sellerId) {
//		DeletedSellerRecords delSellRecord = new DeletedSellerRecords();
		sellDetails = sellRepo.findSellerDetailsBySellerId(sellerId);
		if (sellDetails != null && sellRepo.deleteSellerDetailsBySellerId(sellDetails.getSellerId()) == 1) {
			delSellRecord.setAddress(sellDetails.getAddress());
			delSellRecord.setEmailId(sellDetails.getEmailId());
			delSellRecord.setFirstName(sellDetails.getFirstName());
			delSellRecord.setLastName(sellDetails.getLastName());
			delSellRecord.setGender(sellDetails.getGender());
			delSellRecord.setPhoneNumber(sellDetails.getPhoneNumber());
			delSellRecord.setSellerId(sellDetails.getSellerId());
			delSellRecord.setStreet(sellDetails.getStreet());
			delSellRecord.setUsername(sellDetails.getUsername());
			delSellRepo.save(delSellRecord);
			consumerDetails = null;
			delSellRecord = null;
			return "Seller account removed !";
		}
		return "Account not found !";

	}

	public DeletedSellerRecords getDeletedSellerRecordByEmailId(String emailId) {
		return delSellRepo.findDeletedSellerRecordsByEmailId(emailId);
	}

	public List<DeletedSellerRecords> getDeletedSellerRecordsByFirstName(String name) {
		return delSellRepo.findDeletedSellerRecordsByFirstName(name);
	}
}
