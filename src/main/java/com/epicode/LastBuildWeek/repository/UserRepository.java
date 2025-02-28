package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //Recupero utente da Username
    Optional<User> findByUsername(String username);



    List<User> findAll();
    // ricerche chiavi che non devono essere duplicate!!
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
