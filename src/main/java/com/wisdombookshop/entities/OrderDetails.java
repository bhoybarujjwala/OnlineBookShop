package com.wisdombookshop.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	//@Column(insertable = false, updatable = false)
	private Date oDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date delDate;
	
	private int userId;
	private String orderStatus;
	private int delExeId;
	private double ototal; 
	
	public OrderDetails() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetails(int orderId, Date oDate, Date delDate, int userId, String orderStatus, int delExeId,
			double ototal) {
		this.orderId = orderId;
		this.oDate = oDate;
		this.delDate = delDate;
		this.userId = userId;
		this.orderStatus = orderStatus;
		this.delExeId = delExeId;
		this.ototal = ototal;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getoDate() {
		return oDate;
	}

	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}

	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getDelExeId() {
		return delExeId;
	}

	public void setDelExeId(int delExeId) {
		this.delExeId = delExeId;
	}

	public double getOtotal() {
		return ototal;
	}

	public void setOtotal(double ototal) {
		this.ototal = ototal;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", oDate=" + oDate + ", delDate=" + delDate + ", userId=" + userId
				+ ", orderStatus=" + orderStatus + ", delExeId=" + delExeId + ", ototal=" + ototal + "]";
	}
		/*private double ototal; //1600 = 1000 + 600 ---orders
	userid	purchaseid		orderid bookid qty total
	1015		1			105051  501  	2   1000  --purchase 
	1015		2			105051  502 	1    600
	1015		1			105051  504  	2   1000
	1015		2			105051  505 	1    600
	1045		3			105054  502  	1    600  */
	//(List<purchasedetails> detaillist)
}
