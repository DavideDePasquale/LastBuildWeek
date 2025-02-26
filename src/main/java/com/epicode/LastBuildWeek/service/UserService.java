package com.epicode.LastBuildWeek.service;

import com.cloudinary.utils.ObjectUtils;
import com.epicode.LastBuildWeek.configuration.CloudinaryConfig;
import com.epicode.LastBuildWeek.enumeration.UserRole;
import com.epicode.LastBuildWeek.model.Role;
import com.epicode.LastBuildWeek.model.User;
import com.epicode.LastBuildWeek.payload.UserDTO;
import com.epicode.LastBuildWeek.payload.mapper.UserMapperDTO;
import com.epicode.LastBuildWeek.repository.RoleRepository;
import com.epicode.LastBuildWeek.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired UserRepository userRepository;
  @Autowired UserMapperDTO userMapperDTO;
  @Autowired RoleRepository roleRepository;
  @Autowired CloudinaryConfig cloudinaryConfig;

  public UserDTO registerUser(UserDTO userDTO) throws InterruptedException {

    if (userRepository.existsByEmail(userDTO.getEmail())) {
      throw new IllegalStateException("Email già in uso!");
    }

    // Fetch All roles from database
    Set<Role> availableRoles = new HashSet<>(roleRepository.findAll());

    // Mapper from dto to user
    User user = userMapperDTO.toEntity(userDTO, availableRoles);

    // Save to database
    user = userRepository.save(user);

    userRepository.flush();
    Thread.sleep(200);

    // Mapper from user to dto
    return userMapperDTO.toDto(user);
  }

  public UserDTO getUserById(Long id) {
    User user =
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
    return userMapperDTO.toDto(user);
  }

  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream().map(userMapperDTO::toDto).collect(Collectors.toList());
  }

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new EntityNotFoundException("User non trovato");
    }
    userRepository.deleteById(id);
  }

  public UserDTO updateUser(Long id, UserDTO userDTO) {

    User user =
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utente non trovato"));

    Set<Role> availableRoles = new HashSet<>(roleRepository.findAll());

    user = userMapperDTO.updateUser(userDTO, user, availableRoles);

    user = userRepository.save(user);

    return userMapperDTO.toDto(user);
  }

  public UserDTO uploadImage(UserDTO userDTO, MultipartFile file) throws IOException {

    Map uploadResult = cloudinaryConfig.uploader().uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
   // System.out.println("SONO QUI" + uploadResult.get("url"));
    String imageUrl = (String) uploadResult.get("url");
    userDTO.setAvatar(imageUrl);
    // Fetch All roles from database
    Set<Role> availableRoles = new HashSet<>(roleRepository.findAll());
    User user = userMapperDTO.toEntity(userDTO,availableRoles);
    user = userRepository.save(user);
    return userMapperDTO.toDto(user);
  }
}
