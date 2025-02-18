package com.dairyproject.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table

@Getter
@Setter
public class ConsumerQuery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int QID;

	@OneToOne
	@JoinColumn(name = "consumerId")
	private ConsumerDetails consumerDetails;

	@NotEmpty(message = "Query cannot be empty !")
	@Length(max = 400, message = "Query message cannot be more 400 characters !")
	@Lob
	private String message;

	private String dateTime;



}
