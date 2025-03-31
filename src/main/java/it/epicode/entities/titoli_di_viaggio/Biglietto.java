package it.epicode.entities.titoli_di_viaggio;

import it.epicode.entities.emissioni.Emissione;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
public class Biglietto extends TitoloDiViaggio {
    private boolean validita = true;

    public Biglietto(LocalDate dataEmissione, Emissione luogoEmissione) {
        super(dataEmissione, luogoEmissione);
    }

    public Biglietto() {

    }

    public boolean isValidita() {
        return validita;
    }

    public void setValidita(boolean validita) {
        this.validita = validita;
    }
    @Override
    public String toString() {
        return "[id: " +getId() +  "\nvalidita biglietto: " + validita + "\nemissione: " + getLuogoEmissione().getCitta() + "\ndata emissione: " + getDataEmissione() + "]";
    }
}
