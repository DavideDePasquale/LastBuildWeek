package com.epicode.LastBuildWeek.payload;

import com.epicode.LastBuildWeek.enumeration.ClientType;
import com.epicode.LastBuildWeek.model.Address;
import com.epicode.LastBuildWeek.model.Invoice;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClientDTO {

    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate ultimoContatto;
    private BigDecimal fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;

    private String clientType;

}
