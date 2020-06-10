package com.management.UserMS.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.UserMS.dto.LoginDTO;
import com.management.UserMS.dto.SellerDTO;
import com.management.UserMS.entity.Seller;
import com.management.UserMS.repository.SellerRepository;
import com.management.UserMS.validator.SellerValidator;
import com.management.UserMS.validator.Validator;
@Service
public class SellerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SellerRepository sellerRepo;
	
	public String registerSeller(SellerDTO sellerDTO) throws Exception {
		try {
			SellerValidator.sellerValidate(sellerDTO);
			logger.info("Registration request for user {}", sellerDTO);
			Seller be=sellerDTO.createEntity();
			sellerRepo.save(be);
			return ("new user new created");
		}
		catch(Exception e) {
		throw new Exception ("Exception occured");
		}
	}
	
	public boolean Sellerlogin(LoginDTO loginDTO) {
		
		logger.info("Login request for customer {} with password {}", loginDTO.getEmail(), loginDTO.getPassword());
		Validator.emailid(loginDTO.getEmail());
		Seller optBuyer = sellerRepo.findByEmail(loginDTO.getEmail());
		if (optBuyer.getPassword().equals(loginDTO.getPassword())) {
				return true;
			}
		return false;
	}
	
	public void removeseller(Integer sellerid) {
		//Optional<Buyer> buyer=buyerRepo.findByBuyerId(sellerid);
		sellerRepo.deleteById(sellerid);
	}

}
