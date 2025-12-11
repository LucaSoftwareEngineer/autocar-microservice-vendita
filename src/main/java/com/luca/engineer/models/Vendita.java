package com.luca.engineer.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "vendite")
@ToString
public class Vendita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ven_id")
    private Long idVendita;

    @Column(name="ven_nominativo_cliente", nullable = false)
    private String nominativoCliente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ven_auto_id")
    private Auto auto;

    @Column(name = "ven_data_acquisto", nullable = false)
    private LocalDate dataAcquisto;

    @Column(name = "ven_prezzo", nullable = false)
    private Float prezzo;

}
