package com.epicode.LastBuildWeek.payload.mapper;

import com.epicode.LastBuildWeek.model.Address;
import com.epicode.LastBuildWeek.model.Client;
import com.epicode.LastBuildWeek.payload.AddressDTO;
import com.epicode.LastBuildWeek.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressMapperDTO {

    @Autowired
    ClientRepository clientRepository;

    public AddressDTO toDto(Address entity){
        AddressDTO dto = new AddressDTO();
        dto.setCap(entity.getCap());
        dto.setTipo(entity.getTipo());
        dto.setComune(entity.getComune());
        dto.setClient_id(entity.getClient().getId());
        dto.setCivico(entity.getCivico());
        dto.setVia(entity.getVia());
        dto.setLocalita(entity.getLocalita());
        return dto;
    }

    public Address toEntity(AddressDTO dto){
        Address entity = new Address();
        entity.setCap(dto.getCap());
        entity.setTipo(dto.getTipo());
        entity.setComune(dto.getComune());
        entity.setClient(clientRepository.findById(dto.getClient_id()).orElseThrow(()-> new RuntimeException("Cliente non trovato")));
        entity.setCivico(dto.getCivico());
        entity.setVia(dto.getVia());
        entity.setLocalita(dto.getLocalita());
        return entity;
    }
}
