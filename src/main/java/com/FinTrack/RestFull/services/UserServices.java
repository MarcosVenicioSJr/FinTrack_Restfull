package com.FinTrack.RestFull.services;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FinTrack.RestFull.models.User;
import com.FinTrack.RestFull.repository.UserRepository;

@Service
public class UserServices {

  private static final String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";

  @Autowired
  private UserRepository userRepository;

  public void createUser(User user) {
    if (emailExists(user.getEmail()))
      throw new RuntimeException("Email ja cadastrado");

    if (!validatePassword(user.getPassword()))
      throw new RuntimeException(
          "Senha deve ter no minimo 6 caracteres, pelo menos uma letra e um numero");

    userRepository.save(user);
  }

  public void loginUser(String email, String password) {
    User user = userRepository.findByEmail(email);
    if (user == null || !user.getPassword().equals(password)) {
      throw new RuntimeException("Email ou senha invalidos");
    }
  }

  private boolean emailExists(String email) {
    return userRepository.findByEmail(email) != null;
  }

  private boolean validatePassword(String password) {
    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(password).matches();
  }
}
