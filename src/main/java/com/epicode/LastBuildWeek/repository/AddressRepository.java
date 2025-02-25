package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
