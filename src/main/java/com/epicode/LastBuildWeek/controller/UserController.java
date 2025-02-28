package com.epicode.LastBuildWeek.controller;

import com.epicode.LastBuildWeek.enumeration.UserRole;
import com.epicode.LastBuildWeek.model.User;
import com.epicode.LastBuildWeek.payload.LoginResponse;
import com.epicode.LastBuildWeek.payload.UserDTO;
import com.epicode.LastBuildWeek.payload.mapper.LoginRequest;
import com.epicode.LastBuildWeek.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
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

    @PostMapping(value = "/avatar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDTO> createUserWithPhoto(@RequestPart("user") UserDTO userDTO, @RequestPart("file")MultipartFile file){
         try {
             UserDTO savedUser = userService.uploadImage(userDTO, file);
             return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
         }
    }

}
