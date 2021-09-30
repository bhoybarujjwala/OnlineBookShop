package com.wisdombookshop.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisdombookshop.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {
	User findByUserEmail(String useremail);
	User findByUserProfile(String thumbnail);
	List<User> findByUserRole(String string);
	
}
