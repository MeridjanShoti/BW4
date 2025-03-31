package it.epicode.entities.emissioni;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public abstract class Emissione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int conteggioTitoli = 0;
    private String luogoEmissione;

    public Emissione(String luogoEmissione) {
        this.luogoEmissione = luogoEmissione;
    }
    public Emissione (){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getConteggioTitoli() {
        return conteggioTitoli;
    }

    public void setConteggioTitoli(int conteggioTitoli) {
        this.conteggioTitoli = conteggioTitoli;
    }

    public String getLuogoEmissione() {
        return luogoEmissione;
    }

    public void setLuogoEmissione(String luogoEmissione) {
        this.luogoEmissione = luogoEmissione;
    }

    public boolean titoloValido (){
        return false;
    }
    public void vendiBiglietto (){

    }
    public void vendiTessera (){

    }
    public void controllaValidita (){

    }
    public void caricaAbbonamento (){

    }
    @Override
    public String toString() {
        return "[id: " + id  + "\nLuogo di emissione: " + luogoEmissione + "\ntitoli venduti: " + conteggioTitoli+ "]";
    }
}
