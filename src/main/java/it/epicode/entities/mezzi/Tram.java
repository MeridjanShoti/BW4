package it.epicode.entities.mezzi;

import it.epicode.entities.tratte.Tratta;
import it.epicode.enums.InServizio;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Tram extends Mezzo {
    public Tram( Tratta tratta) {
        super(tratta);
        setCapienza(150);
    }
    public Tram() {
    }
    @Override
    public String toString() {
        return "[Tipo mezzo: tram \nid" + getId() + "\ncapienza: " + getCapienza() + "\nin servizio: " + "\nnumero biglietti vidimati: " + getNumeroBigliettiVidimati() + "]";
    }

}
