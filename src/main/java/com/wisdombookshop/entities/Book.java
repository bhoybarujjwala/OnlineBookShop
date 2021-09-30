package com.wisdombookshop.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wisdombookshop.models.BookDTO;

@Entity
@Table(name="book")
public class Book {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int bookId; 
	private String bookName;
	private String authorName;
	private String bookDetails;
	private String thumbnail;
	private double bookPrice;
	private int inventory;//manytoone
	//@JsonBackReference
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId") // categoryId is FK column in Book table.
	private BookCategory bookcategory;
	
	
	public Book() {
		
	}

	public Book(int bookId, String bookName, String authorName, String bookDetails, String thumbnail, double bookPrice,
			int inventory) {
		System.out.println("1bookconstructor");
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookDetails = bookDetails;
		this.thumbnail = thumbnail;
		this.bookPrice = bookPrice;
		this.inventory = inventory;
	}

	/*
	public Book(int bookId, String bookName, String authorName, String bookDetails, String thumbnail, double bookPrice,
			int inventory, BookCategory bookcategory) {
		System.out.println("2bookconstructor");

		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookDetails = bookDetails;
		this.thumbnail = thumbnail;
		this.bookPrice = bookPrice;
		this.inventory = inventory;
		this.bookcategory = bookcategory;
	}*/

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(String bookDetails) {
		this.bookDetails = bookDetails;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	
	public BookCategory getBookcategory() {
		return bookcategory;
	}

	public void setBookcategory(BookCategory bookcategory) {
		this.bookcategory = bookcategory;
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", bookDetails="
				+ bookDetails + ", thumbnail=" + thumbnail + ", bookPrice=" + bookPrice + ", inventory=" + inventory
				+ "]";
	}
	
	public static BookDTO fromEntity(Book entity) {
		BookDTO dto = new BookDTO();
		BeanUtils.copyProperties(entity, dto,  "thumbanail");
		return dto;
	}
	/*
	public int getCategoryId(BookCategory bookcat) {
		System.out.println("this.bookcategory.getCategoryId()" +this.bookcategory.getCategoryId());
		return bookcat.getCategoryId();
	}
	*/
	
	
	

}
