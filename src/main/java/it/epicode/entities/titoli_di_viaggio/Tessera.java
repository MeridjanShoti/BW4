package it.epicode.entities.titoli_di_viaggio;

import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.persone.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public abstract class Tessera extends TitoloDiViaggio {
    private boolean validitaTessera = true;
    @OneToOne
    private Abbonamento abbonamento = null;
    @OneToOne (mappedBy = "tessera")
    private Utente utente;

    public Tessera(LocalDate dataEmissione, Emissione luogoEmissione, Utente utente) {
        super(dataEmissione, luogoEmissione);
        this.utente = utente;
    }

    public Tessera() {

    }

    public boolean isValiditaTessera() {
        return validitaTessera;
    }

    public void setValiditaTessera(boolean validitaTessera) {
        this.validitaTessera = validitaTessera;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    @Override
    public String toString() {
        return "[id: " +getId() +  "\nvalidita tessera: " + validitaTessera + "\nabbonamento: " + abbonamento + "\nutente: " + utente.getNome() + " " +utente.getCognome()+ "\ndata emissione: " + getDataEmissione() + "\nluogo di emissione: " + getLuogoEmissione().getLuogoEmissione() + "]";
    }
}
