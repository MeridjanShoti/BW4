package it.epicode.entities.persone;

import it.epicode.entities.titoli_di_viaggio.Tessera;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Utente {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String cognome;
    @OneToOne
    private Tessera tessera;

}
