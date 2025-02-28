package com.epicode.LastBuildWeek.controller;

import com.epicode.LastBuildWeek.model.Invoice;
import com.epicode.LastBuildWeek.payload.InvoiceDTO;
import com.epicode.LastBuildWeek.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;



    @PostMapping("/{client_id}")
    public ResponseEntity<InvoiceDTO> createInvoice(@PathVariable Long client_id , @RequestBody @Validated InvoiceDTO invoiceDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.createInvoice(invoiceDto,client_id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id){
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }
    @GetMapping("/client/{client_id}")
    public ResponseEntity<Page<InvoiceDTO>> getAllInvoiceByClient(@PathVariable Long client_id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(invoiceService.getInvoiceByClient(client_id,page,size));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
       return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long id,  @RequestBody InvoiceDTO invoiceDTO){
        return ResponseEntity.ok(invoiceService.updateInvoice(id,invoiceDTO));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<InvoiceDTO>> searchInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String clientName,
            @RequestParam(required = false) String invoiceType,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount) {

        Page<InvoiceDTO> invoices = invoiceService.getInvoices(page, size, sortBy, direction, clientName, invoiceType, date, minAmount, maxAmount);
        return ResponseEntity.ok(invoices);
    }



}
