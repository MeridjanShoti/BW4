package it.epicode.entities.mezzi;

import it.epicode.entities.tratte.Tratta;
import it.epicode.enums.InServizio;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Autobus extends Mezzo {
    public Autobus(InServizio inServizio, Tratta tratta) {
        super(inServizio, tratta);
        setCapienza(50);
    }
    public Autobus() {
    }
    @Override
    public String toString() {
        return "[Tipo mezzo: autobus \nid" + getId() + "\ncapienza: " + getCapienza() + "\nin servizio: " + getInServizio() + "\nnumero biglietti vidimati: " + getNumeroBigliettiVidimati() + "]";
    }

}
