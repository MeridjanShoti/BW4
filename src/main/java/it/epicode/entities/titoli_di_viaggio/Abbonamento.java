package it.epicode.entities.titoli_di_viaggio;

import it.epicode.enums.DurataValidita;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class Abbonamento extends TitoloDiViaggio{
    private DurataValidita durataValidita;
    private LocalDate dataEmissione;
    private LocalDate dataScadenza;
    @OneToOne(mappedBy = "tessera")
    private Tessera tessera;

}
