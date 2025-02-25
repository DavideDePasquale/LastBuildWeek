package com.epicode.LastBuildWeek.payload;

import com.epicode.LastBuildWeek.enumeration.AddressType;
import com.epicode.LastBuildWeek.model.Client;
import com.epicode.LastBuildWeek.model.Comune;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDTO {


    private String via;
    private String civico;
    private String localita;
    private String cap;

    private Comune comune;

    private AddressType tipo;

    private Long client_id;

}
