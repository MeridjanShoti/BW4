package it.epicode.entities.mezzi;

import it.epicode.entities.tratte.Tratta;
import jakarta.persistence.Entity;

@Entity
public class Autobus extends Mezzo {
    public Autobus(Tratta tratta) {
        super(tratta);
        setCapienza(50);
    }

    public Autobus() {
    }

    @Override
    public String toString() {
        return "[Tipo mezzo: autobus \nid" + getId() + "\ncapienza: " + getCapienza() + "\nnumero biglietti vidimati: " + getNumeroBigliettiVidimati() + "]";
    }

}
