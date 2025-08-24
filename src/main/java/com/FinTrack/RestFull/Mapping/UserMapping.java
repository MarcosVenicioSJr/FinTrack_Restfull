package com.FinTrack.RestFull.Mapping;

import org.springframework.stereotype.Component;

import com.FinTrack.RestFull.models.User;
import com.FinTrack.RestFull.models.Requests.UserRequest;

@Component
public class UserMapping {
  public User mapperRequestCreateUserToUser(UserRequest request){
    return new User(
      request.getName(),
      request.getEmail(),
      request.getPassword()
    );
  }
}