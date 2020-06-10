package com.management.UserMS.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.UserMS.dto.BuyerDTO;
import com.management.UserMS.dto.CartDTO;
import com.management.UserMS.dto.LoginDTO;
import com.management.UserMS.dto.ProductDTO;
import com.management.UserMS.dto.WishlistDTO;
import com.management.UserMS.entity.Buyer;
import com.management.UserMS.entity.Cart;
import com.management.UserMS.entity.Wishlist;
import com.management.UserMS.repository.BuyerRepository;
import com.management.UserMS.repository.CartRepository;
import com.management.UserMS.repository.SellerRepository;
import com.management.UserMS.repository.WishlistRepository;
import com.management.UserMS.validator.Validator;


@Service
public class BuyerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BuyerRepository buyerRepo;
	@Autowired
	SellerRepository sellerRepo;
//	
	@Autowired
	WishlistRepository wishlistRepo;
	
	@Autowired
	CartRepository cartRepo;
	

	public boolean login(LoginDTO loginDTO) {
		
		logger.info("Login request for customer {} with password {}", loginDTO.getEmail(), loginDTO.getPassword());
		Validator.emailid(loginDTO.getEmail());
		Buyer optBuyer = buyerRepo.findByEmail(loginDTO.getEmail());
		
			if (optBuyer.getPassword().equals(loginDTO.getPassword())) {
				return true;
			}
		return false;
	
	}

		public String registerBuyer(BuyerDTO buyerDTO) throws Exception {
		
		 {
		logger.info("Registration request for user {}", buyerDTO);
		Buyer be=buyerDTO.createEntity();
		System.out.println("before");
		Validator.buyerValidate(buyerDTO);
		System.out.println("after");
		buyerRepo.save(be);
		return("new user new created");
		}}
		


		public void removebuyer(Integer buyerid) {
			//Optional<Buyer> buyer=buyerRepo.findByBuyerId(buyerid);
			buyerRepo.deleteById(buyerid);
		}
		
		public void addtowishlist(ProductDTO prodDTO) {
			System.out.println("in service");
			WishlistDTO wishlist=new WishlistDTO();
			wishlist.setBuyerid(543);
			wishlist.setProdid(prodDTO.getProdid());
			
		
				WishlistDTO wo= new WishlistDTO();
				Wishlist we= wishlist.createEntity();
				wishlistRepo.save(we);

			
		}
		public void addtocart(CartDTO cartDTO) {
			System.out.println("in service");
			CartDTO cart1=new CartDTO();
			cart1.setBuyerid(500);
			cart1.setProdid(cartDTO.getProdid());
			cart1.setQuantity(cartDTO.getQuantity());
			Cart cart2= cart1.createEntity();
			cartRepo.save(cart2);

			
		}
		public void delete(Integer buyerid) {
			
			wishlistRepo.deleteById(buyerid);
			
		}

	


	
	
	
}