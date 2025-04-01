package it.epicode.entities.mezzi;

import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.entities.tratte.Tratta;
import it.epicode.enums.InServizio;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private int tempoEffettivoPercorrenza;
    private int numeroRipetizioniPercorso = 0;
    private int totaleTempoPercorso = 0;
    private List<Integer> tempiEffettivi = new ArrayList<>();
    double mediaTempoPercorsi;
    public void parti() {

        numeroRipetizioniPercorso++;
        setTempoEffettivoPercorrenza(((int)(Math.random() * 60)));
        tempiEffettivi.add(tempoEffettivoPercorrenza);
        setTotaleTempoPercorso(totaleTempoPercorso + tempoEffettivoPercorrenza);

        mediaTempoPercorsi = (double) totaleTempoPercorso / tempiEffettivi.size();
    }

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
        return "[id: " + id + "\ncapienza: " + capienza + "\nin servizio: " + inServizio + "\nnumero biglietti vidimati: " + numeroBigliettiVidimati + "]";
    }
    public void dettagliPercorsiMezzo() {
        System.out.println("percorso fatto " + this.getNumeroRipetizioniPercorso() + " volte");
        System.out.println("tempo percorrenza effettivo: " + this.getTempoEffettivoPercorrenza() + " minuti");
        System.out.println("tempo percorrenza medio: " + this.getMediaTempoPercorsi() + " minuti");
    }
    public void timbraBigliettoDaId(Biglietto biglietto){
        if (biglietto.isValidita()) {
            Scanner sc = new Scanner(System.in);
            biglietto.timbraBiglietto();
            setNumeroBigliettiVidimati(getNumeroBigliettiVidimati() + 1);
            System.out.println("che anno è?");
            int anno = sc.nextInt();
            sc.nextLine();
            System.out.println("che mese è?");
            int mese = sc.nextInt();
            sc.nextLine();
            System.out.println("che giorno è?");
            int giorno = sc.nextInt();
            sc.nextLine();
            biglietto.setDataTimbro(LocalDate.of(anno, mese, giorno));

        } else {
            System.out.println("biglietto non valido scendi subito stronzo");
        }
    }
}
