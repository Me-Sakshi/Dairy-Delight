package com.dairyproject.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dairyproject.dto.ChangePassword;
import com.dairyproject.dto.ConfirmPurchaseOrder;
import com.dairyproject.dto.ConsumerQueryMessage;
import com.dairyproject.dto.Login;
import com.dairyproject.entities.ConsumerDetails;
import com.dairyproject.entities.FeedBackDetails;
import com.dairyproject.entities.ProductDetails;
import com.dairyproject.entities.PurchaseDetails;
import com.dairyproject.entities.SellerDetails;
import com.dairyproject.exceptions.EmailAddressFoundException;
import com.dairyproject.exceptions.PhoneNumberFoundException;
import com.dairyproject.exceptions.UsernameFoundException;
import com.dairyproject.services.ConsumerServices;
import com.dairyproject.services.FeedBackServices;
import com.dairyproject.services.ProductServices;
import com.dairyproject.services.PurchaseServices;
import com.dairyproject.services.QueryServices;
import com.dairyproject.services.SellerServices;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	private ConsumerServices conServ;	
	
	@Autowired
	private PurchaseServices purchaseServ;
	
	@Autowired
	private SellerServices sellServ;
	
	@Autowired
	private FeedBackServices feedServ;
	
	@Autowired
	private ProductServices proServ;
	
	@Autowired
	private QueryServices queryServ;
	
	@PostMapping("/registerdetails")
	public ConsumerDetails registerNewConsumer(@Valid @RequestBody ConsumerDetails consumerDetails)
			throws EmailAddressFoundException, UsernameFoundException, PhoneNumberFoundException {
		return conServ.registerNewConsumer(consumerDetails);
	}

	@PostMapping("/fetchdetailsbyemail")
	public ConsumerDetails getConsumerDetailsByEmailIdandPassword(@RequestBody Login login, HttpServletResponse response)
			throws UnsupportedEncodingException {
		return conServ.getConsumerDetailsByEmailAndPassword(login.getEmailId(), login.getPassword(),response);
	}

	@GetMapping("/fetchconsumerbyemail")
	public ConsumerDetails getConsumerDetailsByEmailId(@RequestParam String emailId) {
		return conServ.getConsumerDetailsByEmailId(emailId);
	}
	
	@PostMapping("/updatedetails")
	public ConsumerDetails updateConsumerDetails(@RequestBody ConsumerDetails consumerDetails)
			throws UnsupportedEncodingException {
		return conServ.updateConsumerDetails(consumerDetails);
	}

	@PostMapping("/changepassword")
	public String changeConsumerPassword(@RequestBody ChangePassword changePassword)
			throws UnsupportedEncodingException {
		return conServ.changeConsumerPassword(changePassword);
	}

	@PostMapping("/removeaccount")
	public String deleteConsumerByEmailId(@RequestBody Login login) throws UnsupportedEncodingException {
		return conServ.deleteConsumerDetailsByEmailId(login);
	}

	@GetMapping("/getallproducts")
	public Set<ProductDetails> getAllProductDetails() {
		return proServ.getAllProductDetails();
	}

	@GetMapping("/getsellersbyproductlocality")
	public Set<SellerDetails> getSellerDetailsByProductAndLocality(@RequestParam String emailId,
			@RequestParam String productName) {
		return sellServ.getSellerListByLocality(emailId);
	}

	@GetMapping("/getpurchaserecords")
	public List<PurchaseDetails> getPurchaseDetailsByConsumerEmailId(@RequestParam String emailId) {
		return purchaseServ.getPurchaseDetailsByConsumerEmailId(emailId);
	}

	@PostMapping("/placeconfirmorder")
	public PurchaseDetails purchaseConfirmOrderInRecord(@RequestBody ConfirmPurchaseOrder confirmPurchaseOrder) {
		return purchaseServ.insertPurchaseRecord(confirmPurchaseOrder);
	}
	
	@PostMapping("/feedback/submitfeedback")
	public String insertNewFeedBack(@Valid @RequestBody FeedBackDetails feedBackDetails) {
		return feedServ.insertNewFeedBackDetails(feedBackDetails);
	}
	

	@PostMapping("/query/submitquery")
	public String insertNewConsumerQuery(@Valid @RequestBody ConsumerQueryMessage consumerQuery) {
		return queryServ.insertNewConsumerQuery(consumerQuery);
	}
	
}
