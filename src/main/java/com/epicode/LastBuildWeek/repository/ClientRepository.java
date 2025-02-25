package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
