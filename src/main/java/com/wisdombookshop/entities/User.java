package com.wisdombookshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.wisdombookshop.models.UserDTO;

@Entity
@Table(name="user")
public class User {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int userId;
	private String userRole;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userPassword;
	private String userProfile;
	public User() {
		
	}
	
	public User(int userId, String userRole, String userName, String userPhone, String userEmail, String userPassword,
			String userProfile) {
		this.userId = userId;
		this.userRole = userRole;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userProfile = userProfile;
	}
	
	public User(String userRole, String userName, String userPhone, String userEmail, String userPassword) {
		this.userRole = userRole;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userRole=" + userRole + ", userName=" + userName + ", userPhone="
				+ userPhone + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userProfile="
				+ userProfile + "]";
	}
	public static UserDTO fromEntity(User entity) {
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(entity, dto,  "userProfile");
		return dto;
	}
	
}
