package it.epicode.entities.emissioni;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Rivenditore extends Emissione{
    public String nomeRivenditore;
    public Rivenditore(String luogoEmissione, String nomeRivenditore) {
        super(luogoEmissione);
        this.nomeRivenditore = nomeRivenditore;
    }
    public Rivenditore (){}

    public String getNomeRivenditore() {
        return nomeRivenditore;
    }

    public void setNomeRivenditore(String nomeRivenditore) {
        this.nomeRivenditore = nomeRivenditore;
    }
    @Override
    public String toString() {
        return "[id: " + getId()  + "\nLuogo di emissione: " + getCitta() + "\ntitoli venduti:" + getConteggioTitoli()+ "\n nome rivenditore: " + nomeRivenditore + "]";
    }
}
