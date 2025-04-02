package it.epicode.entities.titoli_di_viaggio;

import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.mezzi.Mezzo;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@NamedQuery(name = "Biglietto.findBigliettoByTempo", query = "SELECT COUNT (b) FROM Biglietto b WHERE b.dataTimbro BETWEEN :data1 AND :data2")
@NamedQuery(name = "Biglietto.countBigliettiByMezzo", query = "SELECT COUNT (b) FROM Biglietto b WHERE b.mezzo = :mezzo")
public class Biglietto extends TitoloDiViaggio {
    private boolean validita = true;
    private LocalDate dataTimbro;
    @ManyToOne
    private Mezzo mezzo;
    public Biglietto(LocalDate dataEmissione, Emissione luogoEmissione) {
        super(dataEmissione, luogoEmissione);
    }

    public Biglietto() {

    }

    public LocalDate getDataTimbro() {
        return dataTimbro;
    }

    public void setDataTimbro(LocalDate dataTimbro) {
        this.dataTimbro = dataTimbro;
    }

    public boolean isValidita() {
        return validita;
    }

    public void setValidita(boolean validita) {
        this.validita = validita;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "[id: " +getId() +  "\nvalidita biglietto: " + validita + "\nemissione: " + getLuogoEmissione().getCitta() + "\ndata emissione: " + getDataEmissione() + "]";
    }
    public void timbraBiglietto() {
        this.validita = false;
    }
}
