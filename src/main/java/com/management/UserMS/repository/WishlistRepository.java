package com.management.UserMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.UserMS.entity.Buyer;
import com.management.UserMS.entity.Wishlist;
@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{

}
