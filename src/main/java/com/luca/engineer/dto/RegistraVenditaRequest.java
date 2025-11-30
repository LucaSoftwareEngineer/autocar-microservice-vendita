package com.luca.engineer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistraVenditaRequest {

    private String nominativoCliente;
    private float prezzo;
    private Long idAuto;
    private LocalDate dataAcquisto;
}
