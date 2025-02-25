package com.epicode.LastBuildWeek.controller;

import com.epicode.LastBuildWeek.enumeration.UserRole;
import com.epicode.LastBuildWeek.model.User;
import com.epicode.LastBuildWeek.payload.UserDTO;
import com.epicode.LastBuildWeek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
        UserDTO dto = userService.registerUser(userDTO, UserRole.USER);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updteUser(@PathVariable Long id, UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(id,userDTO));
    }
}
