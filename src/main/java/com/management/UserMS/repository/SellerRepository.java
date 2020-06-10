package com.management.UserMS.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.management.UserMS.entity.Seller;


@Repository
@Transactional
public interface SellerRepository extends JpaRepository<Seller, Integer>{

	Optional<Seller> findById(Integer sellerId);

	Seller findByEmail(String email);



}