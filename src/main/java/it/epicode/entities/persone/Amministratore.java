package it.epicode.entities.persone;

import it.epicode.entities.titoli_di_viaggio.Tessera;
import jakarta.persistence.Entity;

@Entity
public class Amministratore extends Utente {
    public Amministratore(String nome, String cognome, Tessera tessera) {
        super(nome, cognome, tessera);
    }
    public Amministratore(){}
    @Override
    public String toString() {
        return "[AMMINISTRATORE \nNome: " + getNome() + "\nCognome: " + getCognome() + "\nTessera: " + getTessera() + "\nid: " + getId() + "]";
    }
}
