package com.epicode.LastBuildWeek.repository;

import com.epicode.LastBuildWeek.enumeration.AddressType;
import com.epicode.LastBuildWeek.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findByComune_Nome(String comune);
    List<Address> findByTipo(AddressType tipo);
}
