package com.dairyproject.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table

@Getter
@Setter
public class DeletedConsumerRecords {

	@Id
	private int consumerId;
	private String firstName;
	private String lastName;
	private String gender;
	private String phoneNumber;
	private String emailId;
	private String street;
	private String username;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "AID")
	private AddressDetails address;

}
