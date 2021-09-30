package com.wisdombookshop.entities;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "purchasedetails")
public class PurchaseDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchaseId;
	//private int orderId;
	private int bookId;
	private int qty;
	private double total;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId") // orderId is FK column in PurchaseDetails table.
	private OrderDetails orderdetails;
	
	public PurchaseDetails() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseDetails(int purchaseId, int bookId, int qty, double total) {
		this.purchaseId = purchaseId;
		this.bookId = bookId;
		this.qty = qty;
		this.total = total;
	}
	
	public OrderDetails getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(OrderDetails orderdetails) {
		this.orderdetails = orderdetails;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PurchaseDetails [purchaseId=" + purchaseId + ", bookId=" + bookId + ", qty=" + qty + ", total=" + total
				+ "]";
	}

}
