package com.wisdombookshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rating")
public class Rating {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int ratingId;
	private int bookId;
	private double rate;
	private String review;
	private int userId;
	
	public Rating() {
		// TODO Auto-generated constructor stub
	}

	public Rating(int ratingId, int bookId, double rate, String review, int userId) {
		this.ratingId = ratingId;
		this.bookId = bookId;
		this.rate = rate;
		this.review = review;
		this.userId = userId;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Rating [ratingId=" + ratingId + ", bookId=" + bookId + ", rate=" + rate + ", review=" + review
				+ ", userId=" + userId + "]";
	}
	
}
