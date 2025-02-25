package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    List<Invoice> findByClient_Id(Long clientId);
    List<Invoice> findByDataBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Invoice> findByImportoGreaterThan(BigDecimal amount);
}
