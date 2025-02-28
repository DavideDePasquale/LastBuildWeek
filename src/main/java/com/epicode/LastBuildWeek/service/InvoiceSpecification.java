package com.epicode.LastBuildWeek.service;

import com.epicode.LastBuildWeek.model.Invoice;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceSpecification {
    public static Specification<Invoice> filterInvoices(
            String clientName, String invoiceType, LocalDate date,
            BigDecimal minAmount, BigDecimal maxAmount) {

        return (Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();

            if (clientName != null) {
                predicate = cb.and(predicate, cb.like(root.get("client").get("ragioneSociale"), "%" + clientName + "%"));
            }

            if (invoiceType != null) {
                predicate = cb.and(predicate, cb.equal(root.get("type"), invoiceType));
            }

            if (date != null) {
                predicate = cb.and(predicate, cb.equal(root.get("data"), date));
            }

            if (minAmount != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("importo"), minAmount));
            }

            if (maxAmount != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("importo"), maxAmount));
            }

            return predicate;
        };
    }
}

