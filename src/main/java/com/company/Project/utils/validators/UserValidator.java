package com.company.Project.utils.validators;

import com.company.Project.entities.User;
import com.company.Project.services.base.UsersService;
import com.company.Project.utils.validators.base.Validator;

import java.util.List;

public class UserValidator implements Validator<User> {
  private static final int MIN_USERNAME_LENGTH = 4;
  private static final int MAX_USERNAME_LENGTH = 45;
  private static final int MIN_PASSWORD_LENGTH = 4;
  private static final int MAX_PASSWORD_LENGTH = 60;

  @Override
  public boolean isValid(User user) {
    return user!=null && isUsernameValid(user.getUsername()) && isPasswordValid(user.getPassword());
  }

  public boolean isUsernameValid(String username){
    return username!=null && username.length()>=MIN_USERNAME_LENGTH && username.length()<=MAX_USERNAME_LENGTH;
  }
  public boolean isPasswordValid(String password){
    return password!=null && password.length()>=MIN_PASSWORD_LENGTH && password.length()<=MAX_PASSWORD_LENGTH;
  }
}

