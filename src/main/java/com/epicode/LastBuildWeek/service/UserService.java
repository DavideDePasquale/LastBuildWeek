package com.epicode.LastBuildWeek.service;

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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapperDTO userMapperDTO;
    @Autowired
    RoleRepository roleRepository;


    public UserDTO registerUser(UserDTO userDTO, UserRole roleType){
//        if (userRepository.existsByEmail(userDTO.getEmail())){
//            throw new IllegalStateException("Email già in uso!");
//        }
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(roleType).orElseThrow(()-> new RuntimeException("Ruolo non trovato"));
        roles.add(role);
        User user = userMapperDTO.toEntity(userDTO);
        user.setRoles(roles);
        user = userRepository.save(user);
        return userMapperDTO.toDto(user);
    }

    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Ruolo non trovato"));
        return userMapperDTO.toDto(user);
    }
    public List<User> getAllUser(){
        System.out.println("CIAO");
        List<User> list = userRepository.findAll();
        System.out.println(list);
        return list;
    }
    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new EntityNotFoundException("User non trovato");
        }
        userRepository.deleteById(id);
    }
    public UserDTO updateUser(Long id,UserDTO userDTO){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Ruolo non trovato"));
        user = userMapperDTO.updateUser(userDTO,user);
        user = userRepository.save(user);
        return userMapperDTO.toDto(user);
    }
}
