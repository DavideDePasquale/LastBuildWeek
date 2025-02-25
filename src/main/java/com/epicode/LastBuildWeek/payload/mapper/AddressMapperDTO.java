package com.epicode.LastBuildWeek.payload.mapper;

import com.epicode.LastBuildWeek.model.Address;
import com.epicode.LastBuildWeek.model.Client;
import com.epicode.LastBuildWeek.payload.AddressDTO;
import com.epicode.LastBuildWeek.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
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

    public Address updateAddress(AddressDTO addressDTO,Address address){
        if (addressDTO.getCap() != null){
            address.setCap(addressDTO.getCap());
        }
        if (addressDTO.getTipo() != null){
            address.setTipo(addressDTO.getTipo());
        }
        if (addressDTO.getComune() != null){
            address.setComune(addressDTO.getComune());
        }
        if (addressDTO.getClient_id() != null){
            address.setClient(clientRepository.findById(addressDTO.getClient_id()).orElseThrow(()-> new EntityNotFoundException("Cliente non trovato")));
        }
        if (addressDTO.getCivico() != null){
            address.setCivico(addressDTO.getCivico());
        }
        if (addressDTO.getVia() != null){
            address.setVia(addressDTO.getVia());
        }
        if (addressDTO.getLocalita() != null){
            address.setLocalita(addressDTO.getLocalita());
        }
        return address;
    }

}
