package it.epicode.entities.mezzi;

import it.epicode.enums.InServizio;
import jakarta.persistence.Entity;

@Entity
public class Autobus extends Mezzo {
    public Autobus(InServizio inServizio) {
        super(inServizio);
        setCapienza(50);
    }
    public Autobus() {
    }

}
