package com.epicode.LastBuildWeek.model;

import com.epicode.LastBuildWeek.enumeration.ClientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Enumerated(EnumType.STRING)
    private ClientType clientType;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

}
