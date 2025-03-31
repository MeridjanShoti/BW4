package it.epicode.entities.titoli_di_viaggio;

import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.persone.Utente;
import it.epicode.enums.DurataValidita;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "titoli_di_viaggio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_titolo_di_viaggio")
public abstract class TitoloDiViaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEmissione;
    @ManyToOne
    private Emissione luogoEmissione;

    public TitoloDiViaggio(LocalDate dataEmissione, Emissione luogoEmissione) {
        this.dataEmissione = dataEmissione;
        this.luogoEmissione = luogoEmissione;
    }
    public TitoloDiViaggio (){}
    @Override
    public String toString() {
        return "[id: " + id  + "\ndata emissione: " + dataEmissione + "\nluogo di emissione: " + luogoEmissione.getLuogoEmissione() + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public Emissione getLuogoEmissione() {
        return luogoEmissione;
    }

    public void setLuogoEmissione(Emissione luogoEmissione) {
        this.luogoEmissione = luogoEmissione;
    }
}
