package it.epicode.entities.mezzi;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

@Entity
@NamedQuery(name = "InterventoManutenzione.checkManutenzioneByMezzo", query = "SELECT i FROM InterventoManutenzione i WHERE i.mezzoInManutenzione = :mezzoInManutenzione")
@NamedQuery(name = "InterventoManutenzione.checkManutenzioneByData", query = "SELECT i FROM InterventoManutenzione i WHERE i.inizioManutenzione BETWEEN :data1 AND :data2")
@NamedQuery(name = "InterventoManutenzione.checkManutenzioneByTutti", query = "SELECT i FROM InterventoManutenzione i")
//il file è modificato correttamente
public class InterventoManutenzione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate inizioManutenzione;
    private LocalDate fineManutenzione;
    @ManyToOne
    private Mezzo mezzoInManutenzione;

    public InterventoManutenzione(LocalDate inizioManutenzione, LocalDate fineManutenzione, Mezzo mezzoInManutenzione) {
        this.inizioManutenzione = inizioManutenzione;
        this.fineManutenzione = fineManutenzione;
        this.mezzoInManutenzione = mezzoInManutenzione;
    }

    public InterventoManutenzione() {
    }

    public LocalDate getInizioManutenzione() {
        return inizioManutenzione;
    }

    public void setInizioManutenzione(LocalDate inizioManutenzione) {
        this.inizioManutenzione = inizioManutenzione;
        System.out.println("manutenzione iniziata il " + inizioManutenzione);
    }

    public LocalDate getFineManutenzione() {
        return fineManutenzione;
    }

    public void setFineManutenzione(LocalDate fineManutenzione) {
        this.fineManutenzione = fineManutenzione;
        System.out.println("manutenzione finita il " + fineManutenzione);
    }

    public Mezzo getMezzoInManutenzione() {
        return mezzoInManutenzione;
    }

    public void setMezzoInManutenzione(Mezzo mezzoInManutenzione) {
        this.mezzoInManutenzione = mezzoInManutenzione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "[Id Manutenzione: " + id +
                "inizioManutenzione=" + inizioManutenzione +
                ", fineManutenzione=" + fineManutenzione +
                ", mezzoInManutenzione=" + mezzoInManutenzione.getId() + "]";
    }

}
