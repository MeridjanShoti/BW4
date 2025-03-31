package it.epicode.entities.emissioni;

import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.entities.titoli_di_viaggio.TitoloDiViaggio;
import jakarta.persistence.*;

import java.util.Scanner;

@Entity
@Table(name = "emissioni")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_emissione")
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
    public void vendiTitolo (){

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
