package com.wisdombookshop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wisdombookshop.daos.Bookdao;
import com.wisdombookshop.entities.Book;
import com.wisdombookshop.entities.BookCategory;
import com.wisdombookshop.utils.StorageService;

@Service
@Transactional
public class BookServiceImpl implements BookService{
	@Autowired
	private Bookdao dao;
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private BookCategoryService catserv;

	@Override
	public List<Book> findBookAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Book findBookById(int id) {
		Optional<Book> book = dao.findById(id);
		return book.orElse(null);
	}

	
	
	@Override
	public List<Book> getbooksbycatid(int catid){
		BookCategory cat = new BookCategory();
		//cat.setCategoryId(catid);
		//System.out.println(catid);
		List<Book> newlist= new ArrayList<Book>();
		
		List<Book> list = dao.findAll();
		
		for (Book book : list) {
			if(book.getBookcategory() != null) {
				if(((book.getBookcategory().getCategoryId()) == catid))
				newlist.add(book);
			}
		}
		System.out.println(newlist);
		return newlist;
	}
	

	@Override
	public List<Book> findBookLikeTitle(String name) {
		System.out.println(name);
		// TODO Auto-generated method stub
		return dao.findByBookNameContaining(name);
	}
	
	@Override
	public Book save(Book book, MultipartFile thumbnail) {
		String fileName = storageService.store(thumbnail);
		book.setThumbnail(fileName);
		return dao.save(book);
			 
	}
	
	@Override
	public Book save(Book book) {
		return dao.save(book);
			 
	}

	@Override
	public Boolean deleteByid(int id) {
		if(dao.existsById(id)) {
			dao.deleteById(id);
			return true;
		}
		return false;
		
	}

	@Override
	public Resource findByBookThumbnail(String thumbnail) {
		// TODO Auto-generated method stub
		return storageService.load(thumbnail);
	}
	
	
	//ALTER TABLE book ADD FOREIGN KEY (categoryId) REFERENCES BookCategory(categoryId);
	

}
