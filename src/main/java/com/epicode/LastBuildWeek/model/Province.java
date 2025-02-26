package com.epicode.LastBuildWeek.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "provinces")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private String sigla;
    private String regione;
    @OneToMany(mappedBy = "province",cascade = CascadeType.ALL)
    private List<Comune> comunes;

    public Province(String sigla, String nome, String regione, List<Comune> comunes) {
        this.sigla = sigla;
        this.nome = nome;
        this.regione = regione;
        this.comunes = comunes;
    }
}
