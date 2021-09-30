package com.wisdombookshop.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wisdombookshop.entities.BookCategory;
import com.wisdombookshop.entities.User;
import com.wisdombookshop.models.Credential;
import com.wisdombookshop.models.Response;
import com.wisdombookshop.models.UserDTO;
import com.wisdombookshop.services.BookCategoryService;
import com.wisdombookshop.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/wisdombookshop")
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	//login //1. Signin
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(Credential cred) {
		//System.out.println("cred: "+cred);
		if(cred!= null) {
			 User user=userServ.authenticate(cred.getUserEmail(), cred.getUserPassword());
		//System.out.println("userifdjk"+user);
			if(user !=null)
				return Response.success(user);
			return Response.error("Wrong Password");
			}
		return null;
	}
	
	//my-profile
	@PostMapping("/my-profile")
	public ResponseEntity<?> myProfile(User user){
		User currentuser = userServ.findByUserEmail(user.getUserEmail());
		//System.out.println("userinmyprofile"+currentuser);
		if(currentuser != null)
			return Response.success(currentuser);
		return null;
	}
	
	//3. Edit Profile
	@PostMapping("/edit-profile")
	public ResponseEntity<?> editProfile(UserDTO userDto){
		User user = UserDTO.toEntity(userDto);
		//System.out.println(user);
		User currentuser = userServ.findByUserEmail(user.getUserEmail());
		//String password1 = currentuser.getUserPassword();
		if(currentuser != null) {
			currentuser.setUserName(user.getUserName());
			currentuser.setUserPhone(user.getUserPhone());
		}
		//System.out.println(currentuser);
		userServ.edit(currentuser, userDto.getUserProfile());
		if(currentuser != null)
			return Response.success(currentuser);
		return null;
	}
	
	//Change Password
	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(User user){
		//System.out.println(userDto);
		User currentuser = userServ.findByUserEmail(user.getUserEmail());
		if(currentuser!= null)
			currentuser.setUserPassword(user.getUserPassword());
		//System.out.println(currentuser);
		userServ.save(currentuser, currentuser.getUserProfile());
		if(currentuser != null)
			return Response.success(currentuser);
		return null;
	}
	
	//Customer- Register
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(User user) {
		//System.out.println("cred: "+user);
		return Response.success(userServ.save(user, user.getUserProfile()));
		
	}
	
	//Get User Thumbnail
	@GetMapping("/{thumbnail}")
	public  Resource findById(@PathVariable("thumbnail") String thumbnail) {
		 return userServ.findByUserProfile(thumbnail);
	}
	
}
