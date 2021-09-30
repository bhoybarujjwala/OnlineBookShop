package com.wisdombookshop.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisdombookshop.entities.Book;
import com.wisdombookshop.entities.BookCategory;

public interface Bookdao extends JpaRepository<Book, Integer>{
	//List<Book> findByCategoryId(int id);
	List<Book> findByBookNameContaining(String name); // LIKE
	//Book save(Book book, BookCategory cat );

}
