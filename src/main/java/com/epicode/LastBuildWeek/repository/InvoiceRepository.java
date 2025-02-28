package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> , JpaSpecificationExecutor<Invoice> {
    Page<Invoice> findAllByClient_Id(Long clientId, Pageable pageable);
    List<Invoice> findByDataBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Invoice> findByImportoGreaterThan(BigDecimal amount);
}
