package it.epicode.entities.titoli_di_viaggio;

import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.persone.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
public class Tessera extends TitoloDiViaggio {
    private LocalDate dataScadenza = getDataEmissione().plusYears(1);
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

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
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
        return "[id: " +getId() +  "\nscadenza: " + dataScadenza+ "\nabbonamento: " + abbonamento + "\nutente: " + utente.getNome() + " " +utente.getCognome()+ "\ndata emissione: " + getDataEmissione() + "\nluogo di emissione: " + getLuogoEmissione().getCitta() + "]";
    }

    @Override
    public void setDataEmissione(LocalDate dataEmissione) {
        super.setDataEmissione(dataEmissione);
        this.dataScadenza = dataEmissione.plusYears(1);
    }


}
