package com.dairyproject.services;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import com.dairyproject.security.service.JWTService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dairyproject.dto.ChangePassword;
import com.dairyproject.dto.Login;
import com.dairyproject.entities.AddressDetails;
import com.dairyproject.entities.ConsumerDetails;
import com.dairyproject.exceptions.ConsumerNotFoundException;
import com.dairyproject.exceptions.EmailAddressFoundException;
import com.dairyproject.exceptions.IncorrectPasswordException;
import com.dairyproject.exceptions.PhoneNumberFoundException;
import com.dairyproject.exceptions.UnmatchedPasswordException;
import com.dairyproject.exceptions.UsernameFoundException;
import com.dairyproject.repositories.AddressRepository;
import com.dairyproject.repositories.ConsumerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class ConsumerServices {

	@Autowired
	private ConsumerRepository conRepo;

//	@Autowired
	private AddressDetails addressDetails;

	@Autowired
	private AddressRepository addRepo;

	private ConsumerDetails conDetails;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	private JWTService jwtService;
//	@Autowired
//	private DeletedConsumerRecords delConRecord;
//
//	@Autowired
//	private DeletedConsumerRepository delRepo;

	@Autowired
	private DeletedRecordsServices delServ;

	public ConsumerDetails registerNewConsumer(ConsumerDetails consumerDetails)
			throws EmailAddressFoundException, UsernameFoundException, PhoneNumberFoundException {
		int email = conRepo.findConsumerDetailsByEmailId(consumerDetails.getEmailId());
		int username = conRepo.findConsumerDetailsByUsername(consumerDetails.getUsername());
		int phoneNumber = conRepo.findConsumerDetailsByPhoneNumber(consumerDetails.getPhoneNumber());

		addressDetails = addRepo.findAddressDetailsByPincode(consumerDetails.getAddress().getPincode());
		String encryptPassword = bCryptPasswordEncoder.encode(consumerDetails.getPassword());
		if (email == 1) {
			throw new EmailAddressFoundException("Email address already registered");
		} else if (username == 1) {
			throw new UsernameFoundException("Username already taken, please try with another one");
		} else if (phoneNumber == 1) {
			throw new PhoneNumberFoundException("Phone Number already registered");
		} else if (addressDetails != null) {
			consumerDetails.setAddress(addressDetails);
			consumerDetails.setPassword(encryptPassword);
			conRepo.save(consumerDetails);
			addressDetails = null;
			return conRepo.findConsumerDetailsByEmailAndPassword(consumerDetails.getEmailId(),
					encryptPassword);
		} else {
			consumerDetails.setPassword(encryptPassword);
			conRepo.save(consumerDetails);
			return conRepo.findConsumerDetailsByEmailAndPassword(consumerDetails.getEmailId(),
					encryptPassword);
		}

	}

	public ConsumerDetails getConsumerDetailsByEmailAndPassword(String emailId, String password,HttpServletResponse response)
			throws UnsupportedEncodingException {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(emailId, password));
		if (authentication.isAuthenticated()) {
			String jwtToken = jwtService.generateToken(emailId,"CONSUMER");
			response.setHeader("Authorization", "Bearer " + jwtToken);

			return this.conRepo.findByEmailId(emailId).get();
		}
		throw new ConsumerNotFoundException("Consumer not found !");
	}

	public ConsumerDetails getConsumerDetailsByUsernameAndPassword(String username, String password) {
		return conRepo.findConsumerDetailsByUsernameAndPassword(username, password);
	}

	public ConsumerDetails getConsumerDetailsByPhoneNumber(String phoneNumber, String password) {
		return conRepo.findConsumerDetailsByPhoneNumberAndPassword(phoneNumber, password);
	}

	public ConsumerDetails getConsumerDetailsByEmailId(String emailId) {
		return conRepo.findConsumerDetailsByEmailIdOnly(emailId);
	}

	public ConsumerDetails getConsumerDetailsByUsername(String username) {
		return conRepo.findConsumerDetailsByUsernameOnly(username);
	}

	public ConsumerDetails getConsumerDetailsByPhoneNumer(String phoneNumber) {
		return conRepo.findConsumerDetailsByPhoneNumberOnly(phoneNumber);
	}

	public String deleteConsumerDetailsByEmailId(Login login) throws UnsupportedEncodingException {
		return delServ.deleteConsumerByEmailId(login);
	}

	public String deleteConsumerDetailsByConsumerId(Integer consumerId) {
		return delServ.deleteConsumerByConsumerId(consumerId);
	}

	public List<ConsumerDetails> getAllConsumerList() {
		return conRepo.findAllConsumerDetails();
	}

	public List<ConsumerDetails> getConsumerDetailsByFirstName(String firstName) {
		return conRepo.findConsumerByName(firstName);
	}

	public List<ConsumerDetails> getConsumerListByPincode(String pincode) {
		return conRepo.findConsumersByPincode(pincode);
	}

	public List<ConsumerDetails> getConsumerListByDistrict(String district) {
		return conRepo.findConsumersByDistrict(district);
	}

	public List<ConsumerDetails> getConsumerListByTown(String town) {
		return conRepo.findConsumersByTown(town);
	}

	public List<ConsumerDetails> getConsumerByAid(Integer aid) {
		return conRepo.findConsumerDetailsByAid(aid);
	}

	public ConsumerDetails updateConsumerDetails(ConsumerDetails consumerDetails) throws UnsupportedEncodingException {
		ConsumerDetails conDetails = conRepo.findConsumerDetailsByEmailIdOnly(consumerDetails.getEmailId());

		addressDetails = addRepo.findAddressDetailsByPincode(consumerDetails.getAddress().getPincode());
		if (addressDetails != null) {
			conDetails.setAddress(addressDetails);
		} else {
			addRepo.save(consumerDetails.getAddress());
			addressDetails = addRepo.findAddressDetailsByPincode(consumerDetails.getAddress().getPincode());
			conDetails.setAddress(addressDetails);
		}

		conDetails.setStreet(consumerDetails.getStreet());
		conDetails.setFirstName(consumerDetails.getFirstName());
		conDetails.setLastName(consumerDetails.getLastName());
		conDetails.setPhoneNumber(consumerDetails.getPhoneNumber());
//		conDetails.setUsername(consumerDetails.getUsername());
		conRepo.save(conDetails);
		conDetails = null;

		return conRepo.findConsumerDetailsByEmailAndPassword(consumerDetails.getEmailId(),
				consumerDetails.getPassword());

	}
	
	public String changeConsumerPassword(ChangePassword changePassword) throws UnsupportedEncodingException {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String emailId = authentication.getName();

	    Authentication newAuthentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(emailId, changePassword.getOldPassword()));
	    if (newAuthentication.isAuthenticated()) {
	        if (changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
	            ConsumerDetails conDetails = conRepo.findByEmailId(emailId).get();
	            conDetails.setPassword(bCryptPasswordEncoder.encode(changePassword.getNewPassword()));
	            conRepo.save(conDetails);
	            return "Password changed successfully.";
	        } else {
	            throw new UnmatchedPasswordException("Password does not match. Please enter correct password !");
	        }
	    } else {
	        throw new IncorrectPasswordException("Incorrect old password !");
	    }
	}

//	public String changeConsumerPassword(ChangePassword changePassword) throws UnsupportedEncodingException {
//		//String encryptPassword = Base64.getEncoder().encodeToString(changePassword.getOldPassword().getBytes("UTF-8"));
//		conDetails = conRepo.findConsumerDetailsByEmailAndPassword(changePassword.getEmailId(), changePassword.getOldPassword());
//		if (changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
//			if (conDetails != null) {
//				conDetails.setPassword(changePassword.getNewPassword());
//				conRepo.save(conDetails);
//				conDetails = null;
//				return "Password changed successfully.";
//			} else {
//				throw new IncorrectPasswordException("Incorrect old password !");
//			}
//
//		} else {
//			throw new UnmatchedPasswordException("Password does not match. Please enter correct password !");
//		}
//
//	}

}
