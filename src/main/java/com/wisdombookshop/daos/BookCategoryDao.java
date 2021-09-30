package com.wisdombookshop.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisdombookshop.entities.BookCategory;

public interface BookCategoryDao extends JpaRepository<BookCategory, Integer> {
	
	
}
