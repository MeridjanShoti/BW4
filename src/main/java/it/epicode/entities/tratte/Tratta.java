package it.epicode.entities.tratte;

import it.epicode.entities.mezzi.Mezzo;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tratte")
public class Tratta {
    @Id
    @GeneratedValue
    private Long id;
    private String partenza;
    private String capolinea;
    private int tempoPercorrenzaPrevisto;
    @OneToMany(mappedBy = "tratta")
    private List<Mezzo> mezzi;

    public Tratta(String partenza, String capolinea, int tempoPercorrenzaPrevisto, List<Mezzo> mezzi) {
        this.partenza = partenza;
        this.capolinea = capolinea;
        this.tempoPercorrenzaPrevisto = tempoPercorrenzaPrevisto;
        this.mezzi = mezzi;
    }

    public Tratta() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public int getTempoPercorrenzaPrevisto() {
        return tempoPercorrenzaPrevisto;
    }

    public void setTempoPercorrenzaPrevisto(int tempoPercorrenzaPrevisto) {
        this.tempoPercorrenzaPrevisto = tempoPercorrenzaPrevisto;
    }

    public List<Mezzo> getMezzi() {
        return mezzi;
    }

    public void setMezzi(List<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }

    @Override
    public String toString() {
        return "[id: " + id + "\npartenza: " + partenza + "\ncapolinea: " + capolinea + "\ntempo percorrenza previsto: " + tempoPercorrenzaPrevisto + "]";
    }
}
