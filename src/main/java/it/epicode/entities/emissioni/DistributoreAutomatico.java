package it.epicode.entities.emissioni;

import it.epicode.enums.Attivita;
import jakarta.persistence.Entity;

@Entity
public class DistributoreAutomatico extends Emissione{
    private Attivita attivita;

    public DistributoreAutomatico(String luogoEmissione, Attivita attivita) {
        super(luogoEmissione);
        this.attivita = attivita;
    }
    public DistributoreAutomatico (){}

    public Attivita getAttivita() {
        return attivita;
    }

    public void setAttivita(Attivita attivita) {
        this.attivita = attivita;
    }
    @Override
    public String toString() {
        return "[id: " + getId()  + "\nLuogo di emissione: " + getLuogoEmissione() + "\ntitoli venduti:" + getConteggioTitoli()+ "\nattività: " +attivita + "]";
    }
}
