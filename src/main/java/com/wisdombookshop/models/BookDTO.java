package com.wisdombookshop.models;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.wisdombookshop.entities.Book;
import com.wisdombookshop.entities.User;

public class BookDTO {
	private int bookId; 
	private String bookName;
	private String authorName;
	private String bookDetails;
	private MultipartFile thumbnail;
	private double bookPrice;
	private int inventory;
	
	public BookDTO() {
		// TODO Auto-generated constructor stub
	}

	public BookDTO(int bookId, String bookName, String authorName, String bookDetails, MultipartFile thumbnail,
			double bookPrice, int inventory) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookDetails = bookDetails;
		this.thumbnail = thumbnail;
		this.bookPrice = bookPrice;
		this.inventory = inventory;
	}

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

	public MultipartFile getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MultipartFile thumbnail) {
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
	
	public static Book toEntity(BookDTO dto) {
		Book entity = new Book();
		BeanUtils.copyProperties(dto, entity, "thumbnail");
		return entity;
	}
	
	public static BookDTO fromEntity(Book entity) {
		BookDTO dto = new BookDTO();
		BeanUtils.copyProperties(dto, entity, "thumbnail");
		return dto;
	}

	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", bookDetails="
				+ bookDetails + ", thumbnail=" + thumbnail + ", bookPrice=" + bookPrice + ", inventory=" + inventory
				+ "]";
	}
	
}
