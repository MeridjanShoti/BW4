package it.epicode.entities.emissioni;

import it.epicode.enums.Attivita;
import jakarta.persistence.Entity;

@Entity
public class DistributoreAutomatico extends Emissione{
    private Attivita attivita;

}
