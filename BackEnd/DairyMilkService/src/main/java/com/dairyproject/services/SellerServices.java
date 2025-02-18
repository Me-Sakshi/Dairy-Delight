package com.dairyproject.services;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dairyproject.exceptions.*;
import com.dairyproject.security.service.JWTService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dairyproject.dto.ChangePassword;
import com.dairyproject.dto.Login;
import com.dairyproject.dto.SellerProducts;
import com.dairyproject.entities.AddressDetails;
import com.dairyproject.entities.ConsumerDetails;
import com.dairyproject.entities.DeletedSellerRecords;
import com.dairyproject.entities.ProductDetails;
import com.dairyproject.entities.SellerDetails;
import com.dairyproject.repositories.AddressRepository;
import com.dairyproject.repositories.ConsumerRepository;
import com.dairyproject.repositories.DeletedSellerRepository;
import com.dairyproject.repositories.SellerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SellerServices {

	//@Autowired
	private AddressDetails addressDetails;

	@Autowired
	private AddressRepository addRepo;

	@Autowired
	private SellerRepository sellRepo;

	//@Autowired
	private ConsumerDetails consumerDetails;

	//@Autowired
	private SellerDetails sellDetails;

	@Autowired
	private ConsumerRepository conRepo;

	@Autowired
	private DeletedSellerRepository delRepo;

	//@Autowired
	private DeletedSellerRecords delSelRecord;

	@Autowired
	private DeletedRecordsServices delSelServ;


	//@Autowired
	private ProductDetails proDetails;

	@Autowired
	private ProductServices proServ;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	private JWTService jwtService;
//	@Autowired

	public SellerDetails registerNewSeller(SellerDetails sellerDetails)
			throws EmailAddressFoundException, UsernameFoundException, PhoneNumberFoundException {
		int email = sellRepo.findSellerDetailsByEmailId(sellerDetails.getEmailId());
		int username = sellRepo.findSellerDetailsByUsername(sellerDetails.getUsername());
		int phoneNumber = sellRepo.findSellerDetailsByPhoneNumber(sellerDetails.getPhoneNumber());

		addressDetails = addRepo.findAddressDetailsByPincode(sellerDetails.getAddress().getPincode());
		String encryptPassword = bCryptPasswordEncoder.encode(sellerDetails.getPassword());
		if (email == 1) {
			throw new EmailAddressFoundException("Email address already registered");
		} else if (username == 1) {
			throw new UsernameFoundException("Username already taken, please try with another one");
		} else if (phoneNumber == 1) {
			throw new PhoneNumberFoundException("Phone Number already registered");
		} else if (addressDetails != null) {
			sellerDetails.setAddress(addressDetails);
			sellerDetails.setPassword(encryptPassword);
			sellRepo.save(sellerDetails);
			addressDetails = null;
			return sellRepo.findSellerDetailsByEmailAndPassword(sellerDetails.getEmailId(),
					encryptPassword);
		} else {
			sellerDetails.setPassword(encryptPassword);
			sellRepo.save(sellerDetails);
			return sellRepo.findSellerDetailsByEmailAndPassword(sellerDetails.getEmailId(),
					encryptPassword);
		}
	}

	public SellerDetails getSellerDetailsByEmailAndPassword(String emailId, String password, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(emailId, password));
		if (authentication.isAuthenticated()) {
			String jwtToken = jwtService.generateToken(emailId,"SELLER");
			response.setHeader("Authorization", "Bearer " + jwtToken);

			return this.sellRepo.findByEmailId(emailId).get();
		}
		throw new ConsumerNotFoundException("Consumer not found !");
	}

	public SellerDetails getSellerDetailsByUsernameAndPassword(String username, String password) {
		return sellRepo.findSellerDetailsByUsernameAndPassword(username, password);
	}

	public SellerDetails getSellerDetailsByPhoneNumberAndPassword(String phoneNumber, String password) {
		return sellRepo.findSellerDetailsByPhoneNumberAndPassword(phoneNumber, password);
	}

	public SellerDetails getSellerDetailsByEmailId(String emailId) {
		return sellRepo.findSellerDetailsByEmailIdOnly(emailId);
	}

	public SellerDetails getSellerDetailsByUsername(String username) {
		return sellRepo.findSellerDetailsByUsernameOnly(username);
	}

	public SellerDetails getSellerDetailsByPhoneNumer(String phoneNumber) {
		return sellRepo.findSellerDetailsByPhoneNumberOnly(phoneNumber);
	}

	public String deleteSellerDetailsByEmailId(Login login) throws UnsupportedEncodingException {
		return delSelServ.deleteSellerByEmailId(login);
	}

	public String deleteSellerDetailsBySellerId(Integer sellerId) {
		return delSelServ.deleteSellerrBySellerId(sellerId);
	}

	public List<SellerDetails> getAllSellerList() {
		return sellRepo.findAllSellerDetails();
	}

	public List<SellerDetails> getSellerDetailsByFirstName(String firstName) {
		return sellRepo.findSellerByName(firstName);
	}

	public List<SellerDetails> getSellerListByPincode(String pincode) {
		return sellRepo.findSellersByPincode(pincode);
	}

	public List<SellerDetails> getSellerListByDistrict(String district) {
		return sellRepo.findSellersByDistrict(district);
	}

	public List<SellerDetails> getSellerListByTown(String town) {
		return sellRepo.findSellersByTown(town);
	}

	public Set<SellerDetails> getSellerListByLocality(String emailId) {

		consumerDetails = conRepo.findConsumerDetailsByEmailIdOnly(emailId);
		List<SellerDetails> sellersByPincode = sellRepo.findSellersByPincode(consumerDetails.getAddress().getPincode());
		List<SellerDetails> sellersByTown = sellRepo.findSellersByTown(consumerDetails.getAddress().getTown());
		List<SellerDetails> sellersByDistrict = sellRepo
				.findSellersByDistrict(consumerDetails.getAddress().getDistrict());

		Set<SellerDetails> concatinatedSellerSet = new HashSet<>();
		concatinatedSellerSet.addAll(sellersByPincode);
		concatinatedSellerSet.addAll(sellersByTown);
		concatinatedSellerSet.addAll(sellersByDistrict);

		return concatinatedSellerSet;

	}

//	public Set<SellerDetails> getSellersByProductAndLocality(String emailId, String productName) {
//
//		consumerDetails = conRepo.findConsumerDetailsByEmailIdOnly(emailId);
//		System.out.println(consumerDetails);
//		System.out.println(emailId);
//		List<ProductDetails> productDetails = new ArrayList<>(proServ.getAllProductDetails());
//		List<SellerDetails> sellerList = new ArrayList<>();
//
//		for (int i = 0; i < productDetails.size(); i++) {
//			if (productDetails.get(i).getName().equals(productName)) {
//				sellerList = new ArrayList<>(productDetails.get(i).getSellerDetails());
//			}
//		}
//
//		Set<SellerDetails> sellerFinalList = new HashSet<>();
//
//		for (int i = 0; i < sellerList.size(); i++) {
//			if (sellerList.get(i).getAddress().getPincode() == consumerDetails.getAddress().getPincode()) {
//				sellerFinalList.add(sellerList.get(i));
//			}
//
//			if (sellerList.get(i).getAddress().getTown() == consumerDetails.getAddress().getTown()) {
//				sellerFinalList.add(sellerList.get(i));
//			}
//
//			if (sellerList.get(i).getAddress().getDistrict() == consumerDetails.getAddress().getDistrict()) {
//				sellerFinalList.add(sellerList.get(i));
//			}
//		}
//		//consumerDetails = null;
//		productDetails = null;
//		sellerList = null;
//		return sellerFinalList;
//
//	}

	public SellerDetails updateSellerDetails(SellerDetails sellerDetails) {
		sellDetails = sellRepo.findSellerDetailsByEmailIdOnly(sellerDetails.getEmailId());

		addressDetails = addRepo.findAddressDetailsByPincode(sellerDetails.getAddress().getPincode());
		if (addressDetails != null) {
			sellDetails.setAddress(addressDetails);
		} else {
			addRepo.save(sellerDetails.getAddress());
			addressDetails = addRepo.findAddressDetailsByPincode(sellerDetails.getAddress().getPincode());
			sellDetails.setAddress(addressDetails);
		}

		sellDetails.setStreet(sellerDetails.getStreet());
		sellDetails.setFirstName(sellerDetails.getFirstName());
		sellDetails.setLastName(sellerDetails.getLastName());
		sellDetails.setPhoneNumber(sellerDetails.getPhoneNumber());
		sellDetails.setUsername(sellerDetails.getUsername());
		sellRepo.save(sellDetails);

		return sellRepo.findSellerDetailsByEmailAndPassword(sellerDetails.getEmailId(), sellerDetails.getPassword());

	}

	public String changeSellerPassword(ChangePassword changePassword) throws UnsupportedEncodingException {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String emailId = authentication.getName();

	    Authentication newAuthentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(emailId, changePassword.getOldPassword()));
	    if (newAuthentication.isAuthenticated()) {
	        if (changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
	            SellerDetails sellDetails = sellRepo.findByEmailId(emailId).get();
	            sellDetails.setPassword(bCryptPasswordEncoder.encode(changePassword.getNewPassword()));
	            sellRepo.save(sellDetails);
	            return "Password changed successfully.";
	        } else {
	            throw new UnmatchedPasswordException("Password does not match. Please enter correct password !");
	        }
	    } else {
	        throw new IncorrectPasswordException("Incorrect old password !");
	    }
	}


	public Set<ProductDetails> addProducts(SellerProducts sellerProducts) {
		sellDetails = sellRepo.findSellerDetailsByEmailAndPassword(sellerProducts.getEmailId(),
				sellerProducts.getPassword());

		if (sellDetails != null) {
			List<String> list = new ArrayList<String>(sellerProducts.getProductDetails());

			Set<ProductDetails> productDetailsSet = sellDetails.getProductDetails();
			Set<SellerDetails> sellerList = new HashSet<>();

			for (int i = 0; i < list.size(); i++) {
				proDetails = proServ.getProductDetailsByName(list.get(i));
				if (proDetails != null) {
					sellerList = proDetails.getSellerDetails();
					sellerList.add(sellDetails);
					proDetails.setSellerDetails(sellerList);
					proServ.updateProductDetailDetails(proDetails);
					productDetailsSet.add(proDetails);
				} else {
					throw new ProductNotFoundException(list.get(i) + " product not found !");
				}
			}
			sellDetails.setProductDetails(productDetailsSet);
			sellRepo.save(sellDetails);
			proDetails = null;
			sellerList = null;
			productDetailsSet = null;
			sellDetails = null;
			return sellRepo.findSellerDetailsByEmailIdOnly(sellerProducts.getEmailId()).getProductDetails();
		}

		throw new SellerNotFoundException("Seller not found !");

	}

	public Set<ProductDetails> getSellerAllProductDetails(String emailId) {
		sellDetails = sellRepo.findSellerDetailsByEmailIdOnly(emailId);
		if (sellDetails != null) {
			return sellDetails.getProductDetails();
		}

		throw new SellerNotFoundException("Seller not found !");
	}

	public List<SellerDetails> getSellersByProductName(String name) {
		proDetails = proServ.getProductDetailsByName(name);
		return null;
	}

	public String removeProductFromList(String emailId, Integer pid) {
		sellDetails = sellRepo.findSellerDetailsByEmailIdOnly(emailId);
		proDetails = proServ.getProductDetailsByPid(pid);

		List<ProductDetails> productDetails = new ArrayList<>(sellDetails.getProductDetails());

		if (productDetails.remove(proDetails)) {
			sellDetails.setProductDetails(new HashSet<>(productDetails));
			sellRepo.save(sellDetails);
			return "Product removed from your list !";
		}

		throw new ProductNotFoundException("Product not found !");
	}

}
