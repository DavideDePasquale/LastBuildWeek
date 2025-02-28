package com.epicode.LastBuildWeek.service;

import com.epicode.LastBuildWeek.enumeration.InvoiceType;
import com.epicode.LastBuildWeek.model.Client;
import com.epicode.LastBuildWeek.model.Invoice;
import com.epicode.LastBuildWeek.payload.ClientDTO;
import com.epicode.LastBuildWeek.payload.InvoiceDTO;
import com.epicode.LastBuildWeek.payload.mapper.ClientMapperDTO;
import com.epicode.LastBuildWeek.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public Page<ClientDTO> getAllClients(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Client> clientPage = clientRepository.findAll(pageable);

        return clientPage.map(clientMapperDTO::toDto);
    }

    public void deleteClient(Long id){
        if (!clientRepository.existsById(id)){
            throw new EntityNotFoundException("Questo cliente non è stato trovato");
        }
        clientRepository.deleteById(id);
    }

    public ClientDTO updateClient(Long id, ClientDTO clientDTO){
        Client client = clientRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Utente non trovato"));
        client = clientMapperDTO.updateMapper(client,clientDTO);
        client = clientRepository.save(client);
        return clientMapperDTO.toDto(client);
    }

    public Page<ClientDTO> getClients(
            int page, int size, String sortBy, String direction,
            BigDecimal minRevenue, BigDecimal maxRevenue, String partOfName,
            LocalDate insertDate, LocalDate lastContactDate, String province) {

        Specification<Client> spec = ClientSpecification.filterClients(minRevenue, maxRevenue, partOfName, insertDate, lastContactDate, province);

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Client> clientPage = clientRepository.findAll(spec, pageable);
        return clientPage.map(clientMapperDTO::toDto);
    }

}
