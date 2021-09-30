package com.wisdombookshop.services;

import java.util.List;

import com.wisdombookshop.entities.BookCategory;

public interface BookCategoryService {
	BookCategory save(BookCategory book);
	BookCategory findByCategoryId(int id);
	List<BookCategory> findAllCategoryId();

	
}
