package com.dairyproject.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyproject.entities.ProductDetails;
import com.dairyproject.exceptions.ProductNotFoundException;
import com.dairyproject.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServices {

	@Autowired
	private ProductRepository proRepo;

	public Set<ProductDetails> getAllProductDetails() {
		return proRepo.findAllProductDetails();
	}

	public ProductDetails getProductDetailsByName(String name) {
		return proRepo.findProductDetailsByName(name);
	}

	public ProductDetails getProductDetailsByPid(Integer PID) {
		return proRepo.findProductDetailByPid(PID);
	}

	public String insertNewProductDetails(ProductDetails productDetails) {

		if (proRepo.save(productDetails) != null) {
			return "Product Added...";
		}

		return "Failed to add product. Something went wrong !";

	}

	public String updateProductDetailDetails(ProductDetails productDetails) {
		if (proRepo.save(productDetails) != null) {
			return "Product Updated Succesfully.";
		}
		return "Product update failed.";
	}

	public String removeProductByPID(Integer pid) {
		if (proRepo.deleteProductDetailsByPID(pid) == 1) {
			return "Product removed succesfully...";
		}

		throw new ProductNotFoundException("Product not found !");
	}
}
