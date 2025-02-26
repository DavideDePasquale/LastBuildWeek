package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    List<User> findAll();
}
