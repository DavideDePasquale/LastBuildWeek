package com.epicode.LastBuildWeek.model;

import com.epicode.LastBuildWeek.enumeration.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String via;
    private String civico;
    private String localita;
    private String cap;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comune_id")
    private Comune comune;
    @Enumerated(EnumType.STRING)
    private AddressType tipo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

}
