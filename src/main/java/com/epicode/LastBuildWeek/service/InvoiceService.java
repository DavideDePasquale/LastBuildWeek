package com.epicode.LastBuildWeek.service;

import com.epicode.LastBuildWeek.model.Client;
import com.epicode.LastBuildWeek.model.Invoice;
import com.epicode.LastBuildWeek.payload.InvoiceDTO;
import com.epicode.LastBuildWeek.payload.mapper.InvoiceMapperDTO;
import com.epicode.LastBuildWeek.repository.ClientRepository;
import com.epicode.LastBuildWeek.repository.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceMapperDTO invoiceMapperDTO;
    @Autowired
    ClientRepository clientRepository;


    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO, Long cliente_id){
        Client client = clientRepository.findById(cliente_id).orElseThrow(()-> new EntityNotFoundException("Cliente non trovato!"));
        Invoice invoice = invoiceMapperDTO.toEntity(invoiceDTO);
        invoice.setClient(client);
        invoice = invoiceRepository.save(invoice);
        return invoiceMapperDTO.toDtoResponse(invoice);

    }
    public InvoiceDTO getInvoiceById(Long id){
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Fattura non trovata!"));
        return invoiceMapperDTO.toDtoRequest(invoice);
    }
    public void deleteInvoice(Long id){
        if (!invoiceRepository.existsById(id)){
            throw new EntityNotFoundException("Fattura non trovata");
        }
        invoiceRepository.deleteById(id);
    }
    public InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO){
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Fattura non trovata!"));
        invoice = invoiceMapperDTO.updateInvoice(invoice,invoiceDTO);
        invoice = invoiceRepository.save(invoice);
        return invoiceMapperDTO.toDtoRequest(invoice);
    }
    public Page<InvoiceDTO> getInvoiceByClient(Long client_id,int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Invoice> invoicePage = invoiceRepository.findAllByClient_Id(client_id,pageable);
        return invoicePage.map(invoiceMapperDTO::toDtoResponse);
    }



}
