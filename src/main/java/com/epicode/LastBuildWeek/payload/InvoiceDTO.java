package com.epicode.LastBuildWeek.payload;

import com.epicode.LastBuildWeek.enumeration.InvoiceType;
import com.epicode.LastBuildWeek.model.Client;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceDTO {


    private LocalDate data;
    private BigDecimal importo;
    private String numero;

    private String type;

    private Long client_id;
}
