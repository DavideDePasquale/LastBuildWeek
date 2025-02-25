package com.epicode.LastBuildWeek.service;

import com.epicode.LastBuildWeek.model.Client;
import com.epicode.LastBuildWeek.payload.ClientDTO;
import com.epicode.LastBuildWeek.payload.mapper.ClientMapperDTO;
import com.epicode.LastBuildWeek.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {


    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientMapperDTO clientMapperDTO;


    public ClientDTO createClient(ClientDTO clientDTO){
        Client client  = clientMapperDTO.toEntity(clientDTO);
        client.setDataInserimento(LocalDate.now());
        client = clientRepository.save(client);
        return clientMapperDTO.toDto(client);
    }

    public ClientDTO getClientById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente non trovato"));
        return clientMapperDTO.toDto(client);
    }
    public List<ClientDTO> getAllClients(){
        return clientRepository.findAll().stream().map(clientMapperDTO::toDto).collect(Collectors.toList());
    }

    public void deleteClient(Long id){
        if (!clientRepository.existsById(id)){
            throw new EntityNotFoundException("Questo cliente non è stato trovato");
        }
        clientRepository.deleteById(id);
    }

    public ClientDTO updateClient(Long id, ClientDTO clientDTO){
        Client client = clientRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Utente non trovato"));
        client = clientMapperDTO.toEntity(clientDTO);
        client = clientRepository.save(client);
        return clientMapperDTO.toDto(client);
    }



}
