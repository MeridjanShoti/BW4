package it.epicode.entities.titoli_di_viaggio;

import it.epicode.entities.emissioni.Emissione;
import it.epicode.enums.DurataValidita;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Abbonamento extends TitoloDiViaggio {
    @Enumerated(EnumType.STRING)
    private DurataValidita durataValidita;
    private LocalDate dataScadenza;
    @OneToOne
    private Tessera tessera;

    public Abbonamento(DurataValidita durataValidita, LocalDate dataEmissione, Emissione luogoEmissione, Tessera tessera) {
        super(dataEmissione, luogoEmissione);
        this.durataValidita = durataValidita;
        if (durataValidita == DurataValidita.SETTIMANALE) {
            this.dataScadenza = dataEmissione.plusWeeks(1);
        } else if (durataValidita == DurataValidita.MENSILE) {
            this.dataScadenza = dataEmissione.plusMonths(1);
        }
        this.tessera = tessera;
    }
    public Abbonamento (){}

    public DurataValidita getDurataValidita() {
        return durataValidita;
    }

    public void setDurataValidita(DurataValidita durataValidita) {
        this.durataValidita = durataValidita;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }
    @Override
    public String toString() {
        return "[id: " +getId() +  "\nDurata Validita: " + durataValidita + "\nData Scadenza: " + dataScadenza + "\nData Emissione: " + getDataEmissione() + "\nLuogo Emissione: " + getLuogoEmissione().getCitta() + "]";
    }


}