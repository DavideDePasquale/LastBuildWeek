package com.epicode.LastBuildWeek.service;

import com.epicode.LastBuildWeek.payload.mapper.InvoiceMapperDTO;
import com.epicode.LastBuildWeek.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceMapperDTO invoiceMapperDTO;
}
