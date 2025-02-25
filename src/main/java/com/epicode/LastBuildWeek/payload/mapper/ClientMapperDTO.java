package com.epicode.LastBuildWeek.payload.mapper;

import com.epicode.LastBuildWeek.enumeration.ClientType;
import com.epicode.LastBuildWeek.model.Client;
import com.epicode.LastBuildWeek.payload.ClientDTO;

public class ClientMapperDTO {


    public ClientDTO toDto(Client entity){
        ClientDTO dto = new ClientDTO();
        dto.setClientType(entity.getClientType().toString());
        dto.setEmail(entity.getEmail());
        dto.setPec(entity.getPec());
        dto.setEmailContatto(entity.getEmailContatto());
        dto.setDataInserimento(entity.getDataInserimento());
        dto.setCognomeContatto(entity.getCognomeContatto());
        dto.setTelefono(entity.getTelefono());
        dto.setPartitaIva(entity.getPartitaIva());
        dto.setNomeContatto(entity.getNomeContatto());
        dto.setRagioneSociale(entity.getRagioneSociale());
        dto.setFatturatoAnnuale(entity.getFatturatoAnnuale());
        dto.setUltimoContatto(entity.getUltimoContatto());
        dto.setTelefonoContatto(entity.getTelefonoContatto());
        return dto;
    }

    public Client toEntity(ClientDTO dto){
        Client entity = new Client();
        ClientType tipo = ClientType.valueOf(dto.getClientType().toUpperCase());
        entity.setClientType(tipo);
        entity.setEmail(dto.getEmail());
        entity.setPec(dto.getPec());
        entity.setEmailContatto(dto.getEmailContatto());
        entity.setDataInserimento(dto.getDataInserimento());
        entity.setCognomeContatto(dto.getCognomeContatto());
        entity.setTelefono(dto.getTelefono());
        entity.setPartitaIva(dto.getPartitaIva());
        entity.setNomeContatto(dto.getNomeContatto());
        entity.setRagioneSociale(dto.getRagioneSociale());
        entity.setFatturatoAnnuale(dto.getFatturatoAnnuale());
        entity.setUltimoContatto(dto.getUltimoContatto());
        entity.setTelefonoContatto(dto.getTelefonoContatto());
        return entity;
    }
}
