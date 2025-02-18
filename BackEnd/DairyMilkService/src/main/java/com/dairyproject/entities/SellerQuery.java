package com.dairyproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table

@Getter
@Setter
public class SellerQuery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int QID;

	@OneToOne
	@JoinColumn(name = "sellerId")
	private SellerDetails sellerDetails;

	@NotEmpty(message = "Query cannot be empty !")
	@Length(max = 400, message = "Query message cannot be more 400 characters !")
	@Lob
	private String message;

	private String dateTime;

//	public int getQID() {
//		return QID;
//	}
//
//	public void setQID(int qID) {
//		QID = qID;
//	}
//
//	public SellerDetails getSellerDetails() {
//		return sellerDetails;
//	}
//
//	public void setSellerDetails(SellerDetails sellerDetails) {
//		this.sellerDetails = sellerDetails;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public String getDateTime() {
//		return dateTime;
//	}
//
//	public void setDateTime(String dateTime) {
//		this.dateTime = dateTime;
//	}

}
