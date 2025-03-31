package it.epicode.entities.persone;

import it.epicode.entities.titoli_di_viaggio.Tessera;
import jakarta.persistence.*;

@Entity
@Table (name = "utenti")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_utente")
public class Utente {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String cognome;
    @OneToOne
    private Tessera tessera;

    public Utente(String nome, String cognome, Tessera tessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.tessera = tessera;
    }
    public Utente(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }
    @Override
    public String toString() {
        return "[Nome: " + getNome() + "\nCognome: " + getCognome() + "\nTessera: " + getTessera() + "\nid: " + getId() + "]";
    }
}
