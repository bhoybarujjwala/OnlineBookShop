package com.wisdombookshop.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wisdombookshop.daos.UserDao;
import com.wisdombookshop.entities.User;
import com.wisdombookshop.models.Credential;
import com.wisdombookshop.utils.StorageService;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao uDao;
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User findById(int id) {
		Optional<User> user = uDao.findById(id);
		return user.orElse(null);
	}
	@Override
	public User findByUserEmail(String userEmail) {
		return uDao.findByUserEmail(userEmail);
	}
	@Override
	public User authenticate(String userEmail, String password) {
		User authuser = uDao.findByUserEmail(userEmail);
		System.out.println(authuser);
		if(authuser != null && passwordEncoder.matches(password, authuser.getUserPassword())) {
			System.out.println("impl: "+authuser);
			return authuser;
		}
		return null;
	}
	
	@Override
	public User edit(User user, MultipartFile thumbnail) {
		String fileName = storageService.store(thumbnail);
		user.setUserProfile(fileName);
		return uDao.save(user);
	}
	
	@Override
	public User save(User user, String thumbnail) {
		//String fileName = storageService.store(thumbnail);
		String encPassword = passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(encPassword);
		user.setUserProfile(thumbnail);
		user.setUserRole(user.getUserRole());
		return uDao.save(user);
	}
	@Override
	public Resource findByUserProfile(String thumbnail) {
		
		return storageService.load(thumbnail);
	}
	@Override
	public Boolean deleteById(int id) {
		if(uDao.existsById(id)) {
			uDao.deleteById(id);
			return true;
		}
		return false;
	}
	@Override
	public List<User> findByUserRole(String string) {
		System.out.println(uDao.findByUserRole(string));
		return uDao.findByUserRole(string);
	}
	
	

}
