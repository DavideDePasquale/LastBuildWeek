package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long>, JpaSpecificationExecutor<Client> {
    List<Client> findByRagioneSocialeContainingIgnoreCase(String ragioneSociale);
    Optional<Client> findByPartitaIva(String partitaIva);



    //esercizio 2
    Page<Client> findByRagioneSocialeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Client> findByFatturatoAnnualeBetween(BigDecimal min, BigDecimal max, Pageable pageable);
    Page<Client> findByDataInserimentoBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Client> findByUltimoContattoBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
