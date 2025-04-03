package it.epicode.entities.mezzi;

import it.epicode.entities.tratte.Tratta;
import it.epicode.enums.InServizio;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Autobus extends Mezzo {
    public Autobus( Tratta tratta) {
        super(tratta);
        setCapienza(50);
    }
    public Autobus() {
    }
    @Override
    public String toString() {
        return "[Tipo mezzo: autobus \nid" + getId() + "\ncapienza: " + getCapienza() + "\nin servizio: " + "\nnumero biglietti vidimati: " + getNumeroBigliettiVidimati() + "]";
    }

}
