package com.wisdombookshop.models;

public class Information {
	private int userId;
	private String userRole;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userPassword;
	private String userProfile;
	private double salary;
	private int leaves;
	private int managerId;
	private String city;

	public Information() {
		// TODO Auto-generated constructor stub
	}
	
	public Information(int userId, String userRole, String userName, String userPhone, String userEmail,
			String userPassword, String userProfile, double salary, int leaves, int managerId, String city) {
		this.userId = userId;
		this.userRole = userRole;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userProfile = userProfile;
		this.salary = salary;
		this.leaves = leaves;
		this.managerId = managerId;
		this.city = city;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Information [userId=" + userId + ", userRole=" + userRole + ", userName=" + userName + ", userPhone="
				+ userPhone + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userProfile="
				+ userProfile + ", salary=" + salary + ", leaves=" + leaves + ", managerId=" + managerId + ", city="
				+ city + "]";
	}

	
	
}
