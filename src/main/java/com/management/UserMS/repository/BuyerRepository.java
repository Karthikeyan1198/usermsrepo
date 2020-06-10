package com.management.UserMS.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.management.UserMS.entity.Buyer;

@Repository

public interface BuyerRepository extends JpaRepository<Buyer, Integer>{

	Optional<Buyer> findByBuyerId(Integer buyerid);
	//Buyer findByid(Integer buyerid);
//	Optional<Buyer> findById(Integer buyerId, String email);

	Buyer findByEmail(String emailId);



	}