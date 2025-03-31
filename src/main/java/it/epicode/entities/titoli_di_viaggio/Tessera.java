package it.epicode.entities.titoli_di_viaggio;

import it.epicode.entities.persone.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public abstract class Tessera extends TitoloDiViaggio {
    private boolean validitaTessera = true;
    @OneToOne
    private Abbonamento abbonamento;
    @OneToOne (mappedBy = "tessera")
    private Utente utente;
}
