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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapperDTO userMapperDTO;
    @Autowired
    RoleRepository roleRepository;


//    public UserDTO registerUser(UserDTO userDTO, UserRole roleType){
////        if (userRepository.existsByEmail(userDTO.getEmail())){
////            throw new IllegalStateException("Email già in uso!");
////        }
//        Set<UserRole> roles = new CopyOnWriteArraySet<>();
////        Role role = roleRepository.findByName(roleType).orElseThrow(()-> new RuntimeException("Ruolo non trovato"));
//        roles.add(UserRole.valueOf("USER"));
//        User user = userMapperDTO.toEntity(userDTO);
//        user.setRoles(roles);
//        user = userRepository.save(user);
//        return userMapperDTO.toDto(user);
//    }

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

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        Set<Role> roles = new HashSet<>();

        for (Role roleName : userDTO.getRoles()) {
            Role role = roleRepository.findByName(UserRole.valueOf(UserRole.USER.name()))
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }
        user.setRoles(roles);
        user.setRoles(roles);
        roles.forEach(System.out::println);
        for (Role role : user.getRoles()) {
            role.getUsers().add(user);
        }

        System.out.println("🔍 User Roles Before Save: " + user.getRoles());
        return userRepository.save(user);
    }
}
