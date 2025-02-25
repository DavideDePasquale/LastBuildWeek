package com.epicode.LastBuildWeek.payload.mapper;

import com.epicode.LastBuildWeek.enumeration.InvoiceType;
import com.epicode.LastBuildWeek.model.Invoice;
import com.epicode.LastBuildWeek.payload.InvoiceDTO;
import com.epicode.LastBuildWeek.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class InvoiceMapperDTO {

    @Autowired
    ClientRepository clientRepository;


    public InvoiceDTO toDto(Invoice invoice){
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setClient_id(invoice.getId());
        invoiceDTO.setType(invoice.getType().toString());
        invoiceDTO.setData(invoice.getData());
        invoiceDTO.setImporto(invoice.getImporto());
        invoiceDTO.setNumero(invoice.getNumero());
        return invoiceDTO;
    }



    public Invoice toEntity(InvoiceDTO invoiceDTO){
        Invoice invoice = new Invoice();
        invoice.setClient(clientRepository.findById(invoiceDTO.getClient_id()).orElseThrow(()-> new RuntimeException("Cliente non trovato")));
        invoice.setType(InvoiceType.valueOf(invoiceDTO.getType()));
        invoice.setData(invoiceDTO.getData());
        invoice.setImporto(invoiceDTO.getImporto());
        invoice.setNumero(invoiceDTO.getNumero());
        return invoice;
    }
}
