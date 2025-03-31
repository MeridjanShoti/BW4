package it.epicode.entities.titoli_di_viaggio;

import jakarta.persistence.Entity;

@Entity
public class Biglietto extends TitoloDiViaggio {
    private boolean validita = true;
}
