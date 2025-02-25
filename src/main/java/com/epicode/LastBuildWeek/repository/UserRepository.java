package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
