package com.epicode.LastBuildWeek.payload.mapper;

import com.epicode.LastBuildWeek.enumeration.InvoiceType;
import com.epicode.LastBuildWeek.model.Invoice;
import com.epicode.LastBuildWeek.payload.InvoiceDTO;
import com.epicode.LastBuildWeek.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
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
        invoice.setClient(clientRepository.findById(invoiceDTO.getClient_id()).orElseThrow(()-> new EntityNotFoundException("Cliente non trovato!")));
        invoice.setType(InvoiceType.valueOf(invoiceDTO.getType()));
        invoice.setData(invoiceDTO.getData());
        invoice.setImporto(invoiceDTO.getImporto());
        invoice.setNumero(invoiceDTO.getNumero());
        return invoice;
    }

    public Invoice updateInvoice(Invoice invoice, InvoiceDTO invoiceDTO){
        if (invoiceDTO.getClient_id() != null){
            invoice.setClient(clientRepository.findById(invoiceDTO.getClient_id()).orElseThrow(()-> new EntityNotFoundException("Cliente non trovato!")));
        }
        if (invoiceDTO.getType() != null){
            invoice.setType(InvoiceType.valueOf(invoiceDTO.getType()));
        }
        if (invoiceDTO.getData() != null){
            invoice.setData(invoiceDTO.getData());
        }
        if (invoiceDTO.getImporto() != null){
            invoice.setImporto(invoiceDTO.getImporto());
        }
        if (invoiceDTO.getNumero() != null){
            invoice.setNumero(invoiceDTO.getNumero());
        }
        return invoice;
    }
}
