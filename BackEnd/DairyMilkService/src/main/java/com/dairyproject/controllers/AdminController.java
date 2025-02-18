package com.dairyproject.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.dairyproject.dto.Login;
import com.dairyproject.entities.Administrator;
import com.dairyproject.entities.ConsumerDetails;
import com.dairyproject.entities.ConsumerQuery;
import com.dairyproject.entities.DeletedConsumerRecords;
import com.dairyproject.entities.DeletedSellerRecords;
import com.dairyproject.entities.FeedBackDetails;
import com.dairyproject.entities.ProductDetails;
import com.dairyproject.entities.PurchaseDetails;
import com.dairyproject.entities.SellerDetails;
import com.dairyproject.entities.SellerQuery;
import com.dairyproject.services.AdminServices;
import com.dairyproject.services.ConsumerServices;
import com.dairyproject.services.DeletedRecordsServices;
import com.dairyproject.services.FeedBackServices;
import com.dairyproject.services.ProductServices;
import com.dairyproject.services.PurchaseServices;
import com.dairyproject.services.QueryServices;
import com.dairyproject.services.SellerServices;

// elastic IP -> 10.21.11.12 -> EC2 machine -> terminate/restart
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	// 3 4 dependencies
	// Preffered way to inject dependencies in spring boot ->
	//  -> use testing field level
	//  -> use in normal code constructor
	//  -> optional dependency setter based
	
	@Autowired
	private AdminServices adminServ;

	@Autowired
	private ConsumerServices conServ;

	@Autowired
	private SellerServices sellServ;

	@Autowired
	private DeletedRecordsServices delServ;

	@Autowired
	private FeedBackServices feedServ;

	@Autowired
	private QueryServices queryServ;

	@Autowired
	private ProductServices proServ;
	
	@Autowired
	private PurchaseServices purchaseServ;

	@GetMapping("/fetchconsumerbyemail")
	public ConsumerDetails getConsumerDetailsByEmailId(@RequestParam String emailId) {
		return conServ.getConsumerDetailsByEmailId(emailId);
	}

	@GetMapping("/fetchconsumerbyusername")
	public ConsumerDetails getConsumerDetailsByUsername(@RequestParam String username) {
		return conServ.getConsumerDetailsByUsername(username);
	}

	@GetMapping("/fetchconsumerbyphone")
	public ConsumerDetails getConsumerDetailsByPhoneNumber(@RequestParam String phoneNumber) {
		return conServ.getConsumerDetailsByPhoneNumer(phoneNumber);
	}

	
	@GetMapping("/removeconsumeraccount")
	public String deleteConsumerById(@RequestParam Integer consumerId) {
		return conServ.deleteConsumerDetailsByConsumerId(consumerId);
	}

	@GetMapping("/fetchallconsumers")
	public List<ConsumerDetails> getAllConsumers() {
		return conServ.getAllConsumerList();
	}

	@GetMapping("/admin/fetchconsumersbyname")
	public List<ConsumerDetails> getAllConsumersByName(String name) {
		return conServ.getConsumerDetailsByFirstName(name);
	}

	@GetMapping("/fetchconsumersbypincode")
	public List<ConsumerDetails> getAllConsumersByPincode(@RequestParam String pincode) {
		return conServ.getConsumerListByPincode(pincode);
	}

	@GetMapping("/fetchconsumersbydistrict")
	public List<ConsumerDetails> getAllConsumersByDistrict(String district) {
		return conServ.getConsumerListByDistrict(district);
	}

	@GetMapping("/fetchconsumersbytown")
	public List<ConsumerDetails> getAllConsumersByTown(String town) {
		return conServ.getConsumerListByTown(town);
	}

	@GetMapping("/getconsumerbyaid")
	public List<ConsumerDetails> getAllConsumerByAID(Integer aid) {
		return conServ.getConsumerByAid(aid);
	}

	@GetMapping("/getdeletedconsumers")
	public List<DeletedConsumerRecords> getAllDeletedConsumers() {
		return delServ.getDeletedAllConsumerRecords();
	}

	@GetMapping("/getdeletedconsumerbyemail")
	public DeletedConsumerRecords getDeletedConsumerRecordsByEmail(String emailId) {
		return delServ.getDeletedConsumerRecordByEmailId(emailId);
	}

	@GetMapping("/getdeletedconsumerbyname")
	public List<DeletedConsumerRecords> getDeletedConsumerRecordsByName(String name) {
		return delServ.getDeletedConsumerRecordsByFirstName(name);
	}

	/*
	 * Seller :
	 */

	@GetMapping("/fetchsellerbyemail")
	public SellerDetails getSellerDetailsByEmailId(@RequestParam String emailId) {
		return sellServ.getSellerDetailsByEmailId(emailId);
	}

	@GetMapping("/fetchsellerbyusername")
	public SellerDetails getSellerDetailsByUsername(@RequestParam String username) {
		return sellServ.getSellerDetailsByUsername(username);
	}

	@GetMapping("/fetchsellerbyphone")
	public SellerDetails getSellerDetailsByPhoneNumber(@RequestParam String phoneNumber) {
		return sellServ.getSellerDetailsByPhoneNumer(phoneNumber);
	}

	@GetMapping("/removeselleraccount")
	public String deleteSellerById(@RequestParam Integer sellerId) {
		return sellServ.deleteSellerDetailsBySellerId(sellerId);
	}

	@GetMapping("/fetchallsellers")
	public List<SellerDetails> getAllSellers() {
		return sellServ.getAllSellerList();
	}

	@GetMapping("/fetchsellerbyname")
	public List<SellerDetails> getAllSellersByName(@RequestParam String name) {
		return sellServ.getSellerDetailsByFirstName(name);
	}

	@GetMapping("/fetchsellersbypincode")
	public List<SellerDetails> getAllSellersByPincode(@RequestParam String pincode) {
		return sellServ.getSellerListByPincode(pincode);
	}

	@GetMapping("/fetchsellersbydistrict")
	public List<SellerDetails> getAllSellersByDistrict(@RequestParam String district) {
		return sellServ.getSellerListByDistrict(district);
	}

	@GetMapping("/fetchsellersbytown")
	public List<SellerDetails> getAllSellersByTown(@RequestParam String town) {
		return sellServ.getSellerListByTown(town);
	}

	@GetMapping("/getdeletedsellers")
	public List<DeletedSellerRecords> getAllDeletedSellers() {
		return delServ.getDeletedAllSellerRecords();
	}

	@GetMapping("/getdeletedsellerbyname")
	public List<DeletedSellerRecords> getAllDeletedSellerRecordsByName(@RequestParam String name) {
		return delServ.getDeletedSellerRecordsByFirstName(name);
	}

	@GetMapping("/getdeletedsellerbyemail")
	public DeletedSellerRecords getDeletedSellerRecordByEmailId(@RequestParam String emailId) {
		return delServ.getDeletedSellerRecordByEmailId(emailId);
	}

	/*
	 * Feedback and Query
	 */

	@GetMapping("/feedback/id")
	public FeedBackDetails getFeedBackById(@RequestParam Integer fid) {
		return feedServ.getFeedBackDetailsById(fid);
	}

	@GetMapping("/feedback/name")
	public List<FeedBackDetails> getFeedBackByName(@RequestParam String name) {
		return feedServ.getFeedBackDetailsByname(name);
	}

	@GetMapping("/feedback/subject")
	public List<FeedBackDetails> getFeedBackBySubject(@RequestParam String subject) {
		return feedServ.getFeedBackDetailsBySubject(subject);
	}

	@GetMapping("/consumer/query")
	public List<ConsumerQuery> getAllConsumerQueries() {
		return queryServ.getAllConsumerQueries();
	}

	@GetMapping("/consumer/queriesbyemailid")
	public List<ConsumerQuery> getQueriesByConsumerEmailId(@RequestParam String emailId) {
		return queryServ.getConsumerQueriesByConsumerEmailId(emailId);
	}

	@GetMapping("/consumer/queriesbydatetime")
	public List<ConsumerQuery> getConsumerQueriesByDateTime(@RequestParam String dateTime) {
		return queryServ.getConsumerQueriesByDateTime(dateTime);
	}

	@GetMapping("/seller/query")
	public List<SellerQuery> getAllSellerQueries() {
		return queryServ.getAllSellerQueries();
	}

	@GetMapping("/seller/queriesbyemailid")
	public List<SellerQuery> getQueriesBySellerEmailId(@RequestParam String emailId) {
		return queryServ.getSellerQueriesBySellerEmailId(emailId);
	}

	@GetMapping("/seller/queriesbydatetime")
	public List<SellerQuery> getSellerQueriesByDateTime(@RequestParam String dateTime) {
		return queryServ.getSellerQueriesByDateTime(dateTime);
	}

	/*
	 * Products
	 */

	@PostMapping("/products/addnewproduct")
	public String insertNewProductDetails(@RequestBody ProductDetails productDetails) {
		return proServ.insertNewProductDetails(productDetails);
	}

	@GetMapping("/products/removeproduct")
	public String removeProductByPID(@RequestParam Integer pid) {
		return proServ.removeProductByPID(pid);
	}

	@GetMapping("/products/getallpurchasedetails")
	public List<PurchaseDetails> getAllPurchaseDetails() {
		return purchaseServ.getAllPurchaseRecords();
	}

	@GetMapping("/products/getpurchasedetailsbyid")
	public PurchaseDetails getPurchaseDetailsByPurchaseId(@RequestParam int purchaseId) {
		return purchaseServ.getPurchaseDetailsFromPurchaseId(purchaseId);
	}

	/*
	 * Administrator User
	 */

	@GetMapping("/getadmindetails")
	public Administrator getAdminDetails() {
		return adminServ.getAdminDetails();
	}

	@PostMapping("/login")
	public Administrator adminLogin(@RequestBody Login login, HttpServletResponse response) {
		return adminServ.getLoginDetails(login.getEmailId(), login.getPassword(),response);
	}

	@PostMapping("/changepassword")
	public String changeAdminPassword(@RequestBody ChangePassword changePassword) throws UnsupportedEncodingException {
		return adminServ.changeAdminPassword(changePassword);
	}

	
	
	
	
}
