package com.company.Project.services.base;

import com.company.Project.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.InvalidObjectException;
import java.util.List;

public interface UsersService extends UserDetailsService {

  User getUserByUsername(String Username);

  List<User> getAllUsers();

  void create(User user) throws InvalidObjectException;
}
