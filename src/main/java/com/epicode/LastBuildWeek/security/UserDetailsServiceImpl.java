package com.epicode.LastBuildWeek.security;


import com.epicode.LastBuildWeek.model.User;
import com.epicode.LastBuildWeek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with username " + username + " Not found");
        }



        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .authorities(user.get().getRoles().stream()
                        .peek(role -> System.out.println("🔍 Role Object: " + role))
                        .map(role -> {
                            System.out.println("🔍 Role Name: " + role.getName());
                            return new SimpleGrantedAuthority(role.getName().name());
                        })
                        .collect(Collectors.toList()))
                .build();
    }

}

