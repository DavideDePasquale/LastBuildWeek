package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findByRagioneSocialeContainingIgnoreCase(String ragioneSociale);
    Optional<Client> findByPartitaIva(String partitaIva);
}
