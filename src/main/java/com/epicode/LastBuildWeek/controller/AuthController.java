package com.epicode.LastBuildWeek.controller;


import com.epicode.LastBuildWeek.model.User;
import com.epicode.LastBuildWeek.payload.UserDTO;
import com.epicode.LastBuildWeek.payload.mapper.LoginRequest;
import com.epicode.LastBuildWeek.security.AuthService;
import com.epicode.LastBuildWeek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired private UserService userService;
  @Autowired private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) throws InterruptedException {
    UserDTO dto = userService.registerUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    System.out.println("🔥Login request received:" + loginRequest.getUsername());
    return authService.authenticateUser(loginRequest);
  }
}
