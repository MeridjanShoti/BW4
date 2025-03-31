package it.epicode.entities.mezzi;

import it.epicode.enums.InServizio;
import jakarta.persistence.Entity;

@Entity
public class Tram extends Mezzo {
    public Tram(InServizio inServizio) {
        super(inServizio);
        setCapienza(150);
    }
    public Tram() {
    }
}
