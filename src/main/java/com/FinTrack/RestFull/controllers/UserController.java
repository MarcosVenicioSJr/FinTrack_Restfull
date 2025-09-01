package com.FinTrack.RestFull.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.FinTrack.RestFull.Mapping.UserMapping;
import com.FinTrack.RestFull.models.User;
import com.FinTrack.RestFull.models.Requests.LoginRequest;
import com.FinTrack.RestFull.models.Requests.UserRequest;
import com.FinTrack.RestFull.services.UserServices;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserServices userServices;
  @Autowired
  private UserMapping userMapping;

  @PostMapping("create")
  public ResponseEntity<String> createUser(@RequestBody UserRequest entity) {

    User user = userMapping.mapperRequestCreateUserToUser(entity);
    userServices.createUser(user);
    
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).body("Usuario criado com sucesso");
  }

  @PostMapping("login")
  public ResponseEntity<String> loginUser(@RequestBody LoginRequest request){
    userServices.loginUser(request.getEmail(), request.getPassword());

    return ResponseEntity.ok("Login realizado com sucesso");
  }

}
