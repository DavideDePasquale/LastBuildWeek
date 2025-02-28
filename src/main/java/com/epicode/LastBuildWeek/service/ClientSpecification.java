package com.epicode.LastBuildWeek.service;

import com.epicode.LastBuildWeek.model.Client;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClientSpecification {
    public static Specification<Client> filterClients(
            BigDecimal minRevenue, BigDecimal maxRevenue,
            String partOfName, LocalDate insertDate, LocalDate lastContactDate,
            String province) {

        return (Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();

            if (minRevenue != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("fatturatoAnnuale"), minRevenue));
            }

            if (maxRevenue != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("fatturatoAnnuale"), maxRevenue));
            }

            if (partOfName != null) {
                predicate = cb.and(predicate, cb.like(root.get("ragioneSociale"), "%" + partOfName + "%"));
            }

            if (insertDate != null) {
                predicate = cb.and(predicate, cb.equal(root.get("dataInserimento"), insertDate));
            }

            if (lastContactDate != null) {
                predicate = cb.and(predicate, cb.equal(root.get("ultimoContatto"), lastContactDate));
            }

            if (province != null) {
                predicate = cb.and(predicate, cb.equal(root.join("addresses").get("comune").get("provincia"), province));
            }

            return predicate;
        };
    }
}

