package com.management.UserMS.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.management.UserMS.dto.BuyerDTO;
import com.management.UserMS.dto.CartDTO;
import com.management.UserMS.dto.LoginDTO;
import com.management.UserMS.dto.ProductDTO;
import com.management.UserMS.dto.SellerDTO;
import com.management.UserMS.dto.WishlistDTO;
import com.management.UserMS.entity.Wishlist;
import com.management.UserMS.service.BuyerService;
import com.management.UserMS.service.SellerService;
import com.management.UserMS.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BuyerService buyerService;
	@Autowired
	SellerService sellerService;

	@Autowired
    RestTemplate template;
	

	
	// Login
		@PostMapping(value = "/buyer/login",consumes = MediaType.APPLICATION_JSON_VALUE)
		public String login(@RequestBody LoginDTO loginDTO) {
			logger.info("Login request for customer {} with password {}", loginDTO.getEmail(),loginDTO.getPassword());
			if(buyerService.login(loginDTO)) {
				return "Login Successfull! Welcome";
			};
			return "Login Unsuccessfull, check email and password";
			
		}
		
		@PostMapping(value = "/seller/login", consumes = MediaType.APPLICATION_JSON_VALUE)
		public String Sellerlogin(@RequestBody LoginDTO loginDTO) {
			logger.info("Login request for customer {} with password {}", loginDTO.getEmail(),loginDTO.getPassword());
			if(sellerService.Sellerlogin(loginDTO)) {
				return "Logged in successfully!";
			}
			return "Incorrect emailid or password!";
		}
		
		@PostMapping(value="buyer/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public String registerUser(@RequestBody BuyerDTO buyerDTO) {
			try {
			logger.info("Registration request for user {}", buyerDTO);
			System.out.println("before ser");
			buyerService.registerBuyer(buyerDTO);
			System.out.println("after ser");
			return "Successful";
		}catch(Exception e) {
			return("Not sucessfulL");
		}
		}
		@PostMapping(value="seller/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public String registerSeller(@RequestBody SellerDTO sellerDTO) {
			try {
			logger.info("Registration request for user {}", sellerDTO);
			sellerService.registerSeller(sellerDTO);
			return "Successful";
		}catch(Exception e) {
			return("Not sucessful");
		}}
	

		@DeleteMapping(value="/removebuyer/{buyerid}", consumes= MediaType.APPLICATION_JSON_VALUE)
		public String removebuyer(@PathVariable Integer buyerid) {
			logger.info("Request for order removal by buyer {}", buyerid);
			buyerService.removebuyer(buyerid);
			return "Deleted successfully";
			
			
		} 
		@DeleteMapping(value="/removeseller/{sellerid}", consumes= MediaType.APPLICATION_JSON_VALUE)
		public String removeseller(@PathVariable Integer sellerid) {
			logger.info("Request for order removal by seller {}", sellerid);
			sellerService.removeseller(sellerid);
			return "Deleted successfully";
			
			
		} 
		@PostMapping(value="/add/wishlist/{prodid}/{productname}",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public String addtowishlist(@PathVariable Integer prodid,@PathVariable String productname) {
			try {
				logger.info("Add products to wishlist request {}", productname);
				ProductDTO prodDTO=template.getForObject("http://PRODUCTMS"+"/products/productid/"+prodid,ProductDTO.class);
				buyerService.addtowishlist(prodDTO);
				return "Added successfully";
			
			}catch(Exception e) {
				return "Unable to add";
			}
			}
		
		@PostMapping(value="/add/cart/{prodid}/{productname}/{quantity}",  consumes = MediaType.APPLICATION_JSON_VALUE)
		public String addtocart(@PathVariable Integer prodid,@PathVariable String productname, @PathVariable Integer quantity) {
			try {
				logger.info("Add products to order request {}", productname);
				ProductDTO prodDTO=template.getForObject("http://PRODUCTMS"+"/products/productid/"+prodid,ProductDTO.class);
				CartDTO cart=new CartDTO();
				cart.setProdid(prodDTO.getProdid());
				cart.setQuantity(quantity);
				buyerService.addtocart(cart);
				return "Added successfully";
			
			}catch(Exception e) {
				return "Unable to add";
			}
			}
		@DeleteMapping(value="/delete/{buyerid}")
		public String delete(@PathVariable Integer buyerid) {
			try {
				buyerService.delete(buyerid);
				return "deleted successfully";
			}catch(Exception e) {
				return "Unable to delete";
			}
			
		}
}