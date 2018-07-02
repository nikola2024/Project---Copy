package com.company.Project.services;

import com.company.Project.entities.User;
import com.company.Project.repositories.base.GenericRepository;
import com.company.Project.services.base.UsersService;
import com.company.Project.utils.validators.UserValidator;
import com.company.Project.utils.validators.base.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

  private final GenericRepository<User> usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final Validator<User> userValidator;

  @Autowired
  public UsersServiceImpl(GenericRepository<User> usersRepository, PasswordEncoder passwordEncoder, Validator<User> userValidator) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
    this.userValidator = userValidator;
  }

  @Override
  public User getUserByUsername(String Username) {
    User user = usersRepository.getAll()
        .stream()
        .filter(u -> u.getUsername().equals(Username))
        .findFirst()
        .orElse(null);

    return user;
  }

  @Override
  public List<User> getAllUsers(){
    return usersRepository.getAll();
  }

  @Override
  public void create(User user) throws InvalidObjectException {
    if(userValidator.isValid(user)) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      usersRepository.create(user);
    } else{
      throw new InvalidObjectException("Invalid user");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getUserByUsername(username);
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
  }
}
