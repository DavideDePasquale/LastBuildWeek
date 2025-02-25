package com.epicode.LastBuildWeek.payload.mapper;

import com.epicode.LastBuildWeek.enumeration.ClientType;
import com.epicode.LastBuildWeek.model.Client;
import com.epicode.LastBuildWeek.payload.ClientDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ClientMapperDTO {


    public ClientMapperDTO() {
    }

    public ClientDTO toDto(Client entity){
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
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

    public Client updateMapper(Client client, ClientDTO clientDTO){
        if (clientDTO.getClientType() != null ){
            client.setClientType(ClientType.valueOf(clientDTO.getClientType()));
        }
        if (clientDTO.getEmail() != null){
            client.setEmail(clientDTO.getEmail());
        }
        if (clientDTO.getPec() != null){
            client.setPec(clientDTO.getPec());
        }
        if (clientDTO.getEmailContatto() != null){
            client.setEmailContatto(clientDTO.getEmailContatto());
        }
        if (clientDTO.getDataInserimento() != null){
            client.setDataInserimento(clientDTO.getDataInserimento());
        }
        if (clientDTO.getCognomeContatto() != null){
            client.setCognomeContatto(clientDTO.getCognomeContatto());
        }
        if (clientDTO.getTelefono() != null){
            client.setTelefono(clientDTO.getTelefono());
        }
        if (clientDTO.getPartitaIva() != null){
            client.setPartitaIva(clientDTO.getPartitaIva());
        }
        if (clientDTO.getNomeContatto() != null){
            client.setNomeContatto(clientDTO.getNomeContatto());
        }
        if (clientDTO.getRagioneSociale() != null){
            client.setRagioneSociale(clientDTO.getRagioneSociale());
        }
        if (clientDTO.getFatturatoAnnuale() != null){
            client.setFatturatoAnnuale(clientDTO.getFatturatoAnnuale());
        }
        if (clientDTO.getUltimoContatto() != null){
            client.setUltimoContatto(clientDTO.getUltimoContatto());
        }
        if (clientDTO.getTelefonoContatto() != null){
            client.setTelefonoContatto(clientDTO.getTelefonoContatto());
        }
        return client;
    }
}
