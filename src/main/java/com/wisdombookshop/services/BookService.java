package com.wisdombookshop.services;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.wisdombookshop.entities.Book;
import com.wisdombookshop.entities.BookCategory;

public interface BookService {
	List<Book> findBookAll();

	Book findBookById(int id);

	List<Book> findBookLikeTitle(String name);
	
	Book save(Book book, MultipartFile thumbnail);

	List<Book> getbooksbycatid(int catid);
	
	Boolean deleteByid(int id);

	Resource findByBookThumbnail(String thumbnail);

	Book save(Book book);

}
