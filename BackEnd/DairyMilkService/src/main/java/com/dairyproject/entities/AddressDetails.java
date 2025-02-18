package com.dairyproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class AddressDetails 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aid")
	private int AID;

	@NotEmpty(message = "District name required")
	@Length(max = 20, message = "District name cannot be more than 20 characters")
	@Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Please enter correct district name")
	private String district;

	@Length(max = 20, message = "Town name cannot be more than 20 characters")
	@Pattern(regexp = "^[a-zA-Z]{0,20}$", message = "Please enter correct town name")
	private String town;

	@NotEmpty(message = "Pincode required")
	@Length(min = 6, max = 6, message = "Please enter correct 6 digits pincode")
	@Column(length = 6, unique = true)
	@Pattern(regexp = "^[1-9]{1}[0-9]{2}[0-9]{3}$", message = "Please enter correct 6 digits pincode number")
	private String pincode;

	@NotEmpty(message = "State cannot be empty")
	@Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Please enter correct state name")
	private String state;

	@NotEmpty
	private String country;
}
