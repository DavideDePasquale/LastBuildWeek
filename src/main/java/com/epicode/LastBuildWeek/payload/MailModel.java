package com.epicode.LastBuildWeek.payload;

import lombok.Data;

@Data
public class MailModel {
    private String mittente;
    private String destinatario;
    private String oggetto;
    private String contenuto;
}
