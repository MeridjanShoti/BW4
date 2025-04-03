package it.epicode.entities.mezzi;

import it.epicode.entities.tratte.Tratta;
import jakarta.persistence.Entity;

@Entity
public class Tram extends Mezzo {
    public Tram(Tratta tratta) {
        super(tratta);
        setCapienza(150);
    }

    public Tram() {
    }

    @Override
    public String toString() {
        return "[Tipo mezzo: tram \nid" + getId() + "\ncapienza: " + getCapienza() + "\nnumero biglietti vidimati: " + getNumeroBigliettiVidimati() + "]";
    }
}
