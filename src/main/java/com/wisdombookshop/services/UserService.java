package com.wisdombookshop.services;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.wisdombookshop.entities.User;
import com.wisdombookshop.models.Credential;

public interface UserService {
  User findById(int id);
  User findByUserEmail(String userEmail);
  User authenticate(String userEmail, String password);
  User save(User user, String string);
  User edit(User user, MultipartFile thumbnail);
  Resource findByUserProfile(String thumbnail);
  Boolean deleteById(int id);
  List<User> findByUserRole(String string);
}
