package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
