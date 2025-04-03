package it.epicode.entities.mezzi;

import it.epicode.entities.tratte.Tratta;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_mezzo")
public abstract class Mezzo {
    @Id
    @GeneratedValue
    private Long id;
    private int capienza;
    private int numeroBigliettiVidimati = 0;
    @ManyToOne
    private Tratta tratta;

    private int tempoEffettivoPercorrenza;
    private int numeroRipetizioniPercorso = 0;
    private int totaleTempoPercorso = 0;
    private List<Integer> tempiEffettivi = new ArrayList<>();
    double mediaTempoPercorsi;

    public void parti() {

        numeroRipetizioniPercorso++;
        setTempoEffettivoPercorrenza(20 + ((int) (Math.random() * 40)));
        tempiEffettivi.add(tempoEffettivoPercorrenza);
        setTotaleTempoPercorso(totaleTempoPercorso + tempoEffettivoPercorrenza);

        mediaTempoPercorsi = (double) totaleTempoPercorso / tempiEffettivi.size();
    }

    public void vidimaBiglietto() {
        this.numeroBigliettiVidimati++;
    }

    public Mezzo(Tratta tratta) {
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

    public int getNumeroRipetizioniPercorso() {
        return numeroRipetizioniPercorso;
    }

    public void setNumeroRipetizioniPercorso(int numeroRipetizioniPercorso) {
        this.numeroRipetizioniPercorso = numeroRipetizioniPercorso;
    }

    public int getTotaleTempoPercorso() {
        return totaleTempoPercorso;
    }

    public void setTotaleTempoPercorso(int totaleTempoPercorso) {
        this.totaleTempoPercorso = totaleTempoPercorso;
    }

    public List<Integer> getTempiEffettivi() {
        return tempiEffettivi;
    }

    public void setTempiEffettivi(List<Integer> tempiEffettivi) {
        this.tempiEffettivi = tempiEffettivi;
    }

    public double getMediaTempoPercorsi() {
        return mediaTempoPercorsi;
    }

    public void setMediaTempoPercorsi(double mediaTempoPercorsi) {
        this.mediaTempoPercorsi = mediaTempoPercorsi;
    }


    public int getTempoEffettivoPercorrenza() {
        return tempoEffettivoPercorrenza;
    }

    public void setTempoEffettivoPercorrenza(int tempoEffettivoPercorrenza) {
        this.tempoEffettivoPercorrenza = tempoEffettivoPercorrenza;
    }

    @Override
    public String toString() {
        return "[id: " + id + "\ncapienza: " + capienza + "\nnumero biglietti vidimati: " + numeroBigliettiVidimati + "]";
    }

    public void dettagliPercorsiMezzo() {
        System.out.println("percorso fatto " + this.getNumeroRipetizioniPercorso() + " volte");
        System.out.println("tempo percorrenza effettivo: " + this.getTempoEffettivoPercorrenza() + " minuti");
        System.out.println("tempo percorrenza medio: " + this.getMediaTempoPercorsi() + " minuti");
    }

}
