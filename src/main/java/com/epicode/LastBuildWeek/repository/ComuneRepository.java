package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComuneRepository extends JpaRepository<Comune,Long> {
    List<Comune> findByProvince_Sigla(String siglaProvincia);
}
