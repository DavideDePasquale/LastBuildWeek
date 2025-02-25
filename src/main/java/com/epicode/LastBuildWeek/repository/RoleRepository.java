package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.enumeration.UserRole;
import com.epicode.LastBuildWeek.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(UserRole name);
}
