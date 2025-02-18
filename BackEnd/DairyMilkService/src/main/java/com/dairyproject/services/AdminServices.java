package com.dairyproject.services;

import java.io.UnsupportedEncodingException;

import com.dairyproject.security.service.JWTService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dairyproject.dto.ChangePassword;
import com.dairyproject.entities.Administrator;
import com.dairyproject.entities.ConsumerDetails;
import com.dairyproject.exceptions.IncorrectAdminDetect;
import com.dairyproject.exceptions.IncorrectPasswordException;
import com.dairyproject.exceptions.UnmatchedPasswordException;
import com.dairyproject.repositories.AdministratorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminServices {

	@Autowired
	private AdministratorRepository adminRepo;

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JWTService jwtService;
	//@Autowired
	private Administrator adminDetails;

	public Administrator getAdminDetails() {
		return adminRepo.findAdminDetails();
	}

	public String changeAdminPassword(ChangePassword changePassword) throws UnsupportedEncodingException {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String emailId = authentication.getName();

	    Authentication newAuthentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(emailId, changePassword.getOldPassword()));
	    if (newAuthentication.isAuthenticated()) {
	        if (changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
	        	adminDetails = adminRepo.findByEmailId(emailId).get();
	            adminDetails.setPassword(bCryptPasswordEncoder.encode(changePassword.getNewPassword()));
	            adminRepo.save(adminDetails);
	            return "Password changed successfully.";
	        } else {
	            throw new UnmatchedPasswordException("Password does not match. Please enter correct password !");
	        }
	    } else {
	        throw new IncorrectPasswordException("Incorrect old password !");
	    }
	}
//	
//	public String changeAdminPassword(ChangePassword changePassword) throws UnsupportedEncodingException {
//		adminDetails = adminRepo.findAdminDetails();
//		if (adminDetails.getPassword().equals(changePassword.getOldPassword())) {
//			if (changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
//				adminDetails.setPassword(changePassword.getNewPassword());
//				adminRepo.save(adminDetails);
//				return "Administrator Password Changed...!";
//			} else {
//				throw new UnmatchedPasswordException("Passoword does not match. please enter correct password");
//			}
//		} else {
//			throw new IncorrectPasswordException("Incorrect Old Password !");
//		}
//	}

	public Administrator getLoginDetails(String emailId, String password, HttpServletResponse response) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(emailId, password));
		if (authentication.isAuthenticated()) {
			adminDetails = this.adminRepo.findByEmailId(emailId).get();;
			String jwtToken = jwtService.generateToken(emailId,"ADMIN");

			// Set token in response header
			response.setHeader("Authorization", "Bearer " + jwtToken);

			return adminDetails;
		}
		throw new IncorrectAdminDetect("Incorrect Administrator Credentials");
	}

}
