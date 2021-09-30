package com.wisdombookshop.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empdata")
public class EmpData {
	@Id
	private int userId;
	private double salary;
	private int leaves;
	private String leavestatus;
	//@OneToMany(mappedBy = "empdata",  cascade = CascadeType.ALL)
	private int managerId;
	private String city;

	public EmpData() {
		// TODO Auto-generated constructor stub
	}

	public EmpData(int userId, double salary, int leaves, int managerId) {
		this.userId = userId;
		this.salary = salary;
		this.leaves = leaves;
		this.managerId = managerId;
	}

	public EmpData(int userId, double salary, int leaves, String leavestatus, int managerId, String city) {
		this.userId = userId;
		this.salary = salary;
		this.leaves = leaves;
		this.leavestatus = leavestatus;
		this.managerId = managerId;
		this.city = city;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getLeaves() {
		return leaves;
	}

	public void setLeaves(int leaves) {
		this.leaves = leaves;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	
	public String getLeavestatus() {
		return leavestatus;
	}

	public void setLeavestatus(String leavestatus) {
		this.leavestatus = leavestatus;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "EmpData [userId=" + userId + ", salary=" + salary + ", leaves=" + leaves + ", leavestatus="
				+ leavestatus + ", managerId=" + managerId + ", city=" + city + "]";
	}

	
}
