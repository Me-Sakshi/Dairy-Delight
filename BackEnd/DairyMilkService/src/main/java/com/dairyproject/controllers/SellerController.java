package com.dairyproject.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import com.dairyproject.services.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dairyproject.dto.ChangePassword;
import com.dairyproject.dto.ConsumerQueryMessage;
import com.dairyproject.dto.Login;
import com.dairyproject.dto.LoginByPhone;
import com.dairyproject.dto.LoginByUsername;
import com.dairyproject.dto.SellerProducts;
import com.dairyproject.entities.FeedBackDetails;
import com.dairyproject.entities.ProductDetails;
import com.dairyproject.entities.PurchaseDetails;
import com.dairyproject.entities.SellerDetails;
import com.dairyproject.exceptions.EmailAddressFoundException;
import com.dairyproject.exceptions.PhoneNumberFoundException;
import com.dairyproject.exceptions.UsernameFoundException;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private SellerServices sellServ;
	
	@Autowired
	private PurchaseServices purchaseServ;

	@Autowired
	private QueryServices queryServ;
	
	@Autowired
	private FeedBackServices feedServ;

	@Autowired
	private ProductServices proServ;
	

	@PostMapping("/registerdetails")
	public SellerDetails registeredNewSeller(@Valid @RequestBody SellerDetails sellerDetails)
			throws EmailAddressFoundException, UsernameFoundException, PhoneNumberFoundException {
		return sellServ.registerNewSeller(sellerDetails);
	}

	@PostMapping("/fetchdetailsbyemail")
	public SellerDetails getSellerDetailsByEmailIdandPassword(@RequestBody Login login, HttpServletResponse response)
			throws UnsupportedEncodingException {
		return sellServ.getSellerDetailsByEmailAndPassword(login.getEmailId(), login.getPassword(),response);
	}

	@PostMapping("/fetchdetailsbyusername")
	public SellerDetails getSellerDetailsByUsernameAndPassword(@RequestBody LoginByUsername loginByUsername) {
		return sellServ.getSellerDetailsByUsernameAndPassword(loginByUsername.getUsername(),
				loginByUsername.getPassword());
	}

	@PostMapping("/fetchdetailsbyphonenumber")
	public SellerDetails getSellerDetailsByPhoneNumberAndPassword(@RequestBody LoginByPhone loginByPhone) {
		return sellServ.getSellerDetailsByPhoneNumberAndPassword(loginByPhone.getPhoneNumber(),
				loginByPhone.getPassword());
	}

	@GetMapping("/getallproducts")
	public Set<ProductDetails> getAllProductDetails() {
		return proServ.getAllProductDetails();
	}

	@PostMapping("/updatedetails")
	public SellerDetails updateSellerDetails(@RequestBody SellerDetails sellerDetails) {
		return sellServ.updateSellerDetails(sellerDetails);
	}

	@PostMapping("/changepassword")
	public String changeSellerPassword(@RequestBody ChangePassword changePassword) throws UnsupportedEncodingException {
		return sellServ.changeSellerPassword(changePassword);
	}

	@PostMapping("/removeaccount")
	public String deleteSellerByEmailId(@RequestBody Login login) throws UnsupportedEncodingException {
		return sellServ.deleteSellerDetailsByEmailId(login);
	}

	@GetMapping("/fetchnearbysellers")
	public Set<SellerDetails> getSellersByLocality(@RequestParam String emailId) {
		return sellServ.getSellerListByLocality(emailId);
	}

	@PostMapping("/products/addproducts")
	public Set<ProductDetails> addProducts(@RequestBody SellerProducts sellerProducts) {
		return sellServ.addProducts(sellerProducts);
	}

	/*
	 * Seller :
	 */

	@GetMapping("/fetchsellerbyemail")
	public SellerDetails getSellerDetailsByEmailId(@RequestParam String emailId) {
		return sellServ.getSellerDetailsByEmailId(emailId);
	}

	@GetMapping("/products/getallproducts")
	public Set<ProductDetails> getSellerALlProductDetails(@RequestParam String emailId) {
		return sellServ.getSellerAllProductDetails(emailId);
	}

	@GetMapping("/product/removeproduct")
	public String removeProductByProductName(@RequestParam String emailId, @RequestParam Integer pid) {
		return sellServ.removeProductFromList(emailId, pid);
	}

	@GetMapping("/getpurchaserecords")
	public List<PurchaseDetails> getPurchaseDetailsBySellerEmailId(@RequestParam String emailId) {
		return purchaseServ.getPurchaseDetailsBySellerEmailId(emailId);
	}
     
	
	@GetMapping("/changedeliverystatus")
	public void changeDeliveryStatus(@RequestParam int purchaseId, @RequestParam String status) {
		purchaseServ.changeDeliveryStatusBySeller(purchaseId, status);
	}
	@PostMapping("/feedback/submitfeedback")
	public String insertNewFeedBack(@Valid @RequestBody FeedBackDetails feedBackDetails) {
		return feedServ.insertNewFeedBackDetails(feedBackDetails);
	}

     
	@PostMapping("/query/submitquery")
	public String insertNewSellerQuery(@Valid @RequestBody ConsumerQueryMessage sellerQuery) {
		return queryServ.insertNewSellerQuery(sellerQuery);
	}

}
