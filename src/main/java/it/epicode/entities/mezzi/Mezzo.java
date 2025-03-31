package it.epicode.entities.mezzi;

import it.epicode.entities.tratte.Tratta;
import it.epicode.enums.InServizio;
import jakarta.persistence.*;

@Entity
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_mezzo")
public abstract class Mezzo {
    @Id
    @GeneratedValue
    private Long id;
    private int capienza;
    private InServizio inServizio;
    private int numeroBigliettiVidimati = 0;
    @ManyToOne
    private Tratta tratta;

    public void vidimaBiglietto() {
        this.numeroBigliettiVidimati++;
    }

    public Mezzo(InServizio inServizio, Tratta tratta) {
        this.inServizio = inServizio;
        this.tratta = tratta;
    }

    public Mezzo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public InServizio getInServizio() {
        return inServizio;
    }

    public void setInServizio(InServizio inServizio) {
        this.inServizio = inServizio;
    }

    public int getNumeroBigliettiVidimati() {
        return numeroBigliettiVidimati;
    }

    public void setNumeroBigliettiVidimati(int numeroBigliettiVidimati) {
        this.numeroBigliettiVidimati = numeroBigliettiVidimati;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    @Override
    public String toString() {
        return "[id" + id + "\ncapienza: " + capienza + "\nin servizio: " + inServizio + "\nnumero biglietti vidimati: " + numeroBigliettiVidimati + "]";
    }
}
