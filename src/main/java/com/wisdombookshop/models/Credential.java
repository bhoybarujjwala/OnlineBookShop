package com.wisdombookshop.models;

public class Credential {
	private String userEmail;
	private String userPassword;
	public Credential() {
		super();
	}
	public Credential(String userEmail, String userPassword) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
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
	@Override
	public String toString() {
		return "Credential [userEmail=" + userEmail + ", userPassword=" + userPassword + "]";
	}
	
}
