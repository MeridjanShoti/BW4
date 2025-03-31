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
    private int conteggioTitoli;
    private String luogoEmissione;

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

}
