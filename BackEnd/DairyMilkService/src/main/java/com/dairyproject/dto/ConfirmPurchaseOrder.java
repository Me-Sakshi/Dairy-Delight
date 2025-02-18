package com.dairyproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmPurchaseOrder {

	@Email
	private String consumerEmailId;
	@Email
	private String sellerEmailId;
	private String productName;
	@NotNull
	private int quantity;
	private String paymentMode;



}
