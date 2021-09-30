package com.wisdombookshop.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="bookcategory")
public class BookCategory {
	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int categoryId;
	private String categoryName;
	//@JsonManagedReference
	//@OneToMany(mappedBy = "bookcategory", cascade = CascadeType.ALL)// FK field into Emp class --> dept.deptno
	//private List<Book> bookList;
	
	public BookCategory() {
		//this.bookList = new ArrayList<Book>();
	}

	public BookCategory(int categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		//this.bookList = new ArrayList<Book>();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
	
	/*
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}*/
	/*
	public void addBook(Book book) {
		this.bookList.add(book);
		book.setBookcategory(this);
		System.out.println(this);
	}*/

	@Override
	public String toString() {
		return "BookCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

	
	
	
}
