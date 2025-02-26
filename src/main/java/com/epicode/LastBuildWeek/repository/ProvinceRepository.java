package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province,Long> {
    Optional<Province> findByNome(String nome);
    Optional<Province> findById(Long id);
    Optional<Province> findByNomeIgnoreCase(String nome);
}
