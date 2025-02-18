package com.dairyproject.services;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dairyproject.entities.AddressDetails;
import com.dairyproject.repositories.AddressRepository;

@Service
@Transactional

public class AddressServices {

	@Autowired
	private AddressRepository addRepo;

	public AddressDetails getAddressDetailsByPincode(String pincode) {
		return addRepo.findAddressDetailsByPincode(pincode);
	}

	public List<AddressDetails> getAddressDetailsByDistrict(String district) {
		return addRepo.findAddressDetailsByDistrict(district);
	}

	public AddressDetails getAddressDetailsByTown(String town) {
		return addRepo.findAddressDetailsByTown(town);
	}

}
