package com.wisdombookshop.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisdombookshop.entities.Book;
import com.wisdombookshop.entities.Rating;
import com.wisdombookshop.models.Response;
import com.wisdombookshop.services.BookCategoryService;
import com.wisdombookshop.services.BookService;
import com.wisdombookshop.services.RatingServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/wisdombookshop/book")
public class CommonController {
	@Autowired
	private BookService bookServ;
	
	@Autowired
	private RatingServiceImpl rateServ;
	
	@Autowired
	private BookCategoryService catServ;
	
	
	
	@GetMapping("/similar-books/{bookId}")
	public ResponseEntity<?> similarbooks(@PathVariable("bookId") int bookId){
		Book book = bookServ.findBookById(bookId);
		//System.out.println(book);
		int catid = book.getBookcategory().getCategoryId();
		//System.out.println(catid);
		List<Book> newlist = new ArrayList<Book>();
		List<Book> booklist = bookServ.getbooksbycatid(catid);
		//System.out.println(booklist.size());
		if(booklist.size() >= 3) {
			for (int i = 0; i <3; i++) { //todo
				newlist.add(booklist.get(i));
			}
		}
		return Response.success(newlist);
	}
	
	@GetMapping("/top-rated")
	public ResponseEntity<?> toprated(){
		List<Book> booklist = rateServ.findtoprated();
		if(booklist !=null)
			return Response.success(booklist);
		else
			return Response.error("error");
	}
	
	@GetMapping("/suggested-books")
	public ResponseEntity<?> suggestedBooks(){
		int catId=105;
		if(bookServ.getbooksbycatid(catId) != null)
			return Response.success(bookServ.getbooksbycatid(catId));
		return Response.error("error");
	}
	
	@GetMapping("/{thumbnail}")
	public  Resource findById(@PathVariable("thumbnail") String thumbnail) {
		if(bookServ.findByBookThumbnail(thumbnail) != null)
		  return bookServ.findByBookThumbnail(thumbnail);
		return null;
	}
	
}
