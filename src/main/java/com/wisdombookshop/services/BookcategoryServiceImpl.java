package com.wisdombookshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisdombookshop.daos.BookCategoryDao;
import com.wisdombookshop.entities.BookCategory;

@Service
@Transactional
public class BookcategoryServiceImpl implements BookCategoryService {
	@Autowired
	private BookCategoryDao catDao;
	
	@Override
	public BookCategory save(BookCategory bookcat) {
		// TODO Auto-generated method stub
		System.out.println(bookcat);
		return catDao.save(bookcat);
	}

	@Override
	public BookCategory findByCategoryId(int id) {
		Optional<BookCategory> bookcat = catDao.findById(id);
		return bookcat.orElse(null);
	}
	
	@Override
	public List<BookCategory> findAllCategoryId() {
		return catDao.findAll();
	}

	
}
