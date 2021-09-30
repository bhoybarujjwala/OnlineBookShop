package com.wisdombookshop.models;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wisdombookshop.entities.User;

public class UserDTO {
	private int userId;
	private String userRole;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userPassword;
	private MultipartFile userProfile;
	public UserDTO() {
		
	}
	
	public UserDTO(int userId, String userRole, String userName, String userPhone, String userEmail,
			String userPassword, MultipartFile userProfile) {
		this.userId = userId;
		this.userRole = userRole;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userProfile = userProfile;
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
	public MultipartFile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(MultipartFile userProfile) {
		this.userProfile = userProfile;
	}
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public static User toEntity(UserDTO dto) {
		User entity = new User();
		BeanUtils.copyProperties(dto, entity, "userProfile");
		return entity;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userRole=" + userRole + ", userName=" + userName + ", userPhone="
				+ userPhone + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userProfile="
				+ userProfile + "]";
	}
	
	
	
	
}
