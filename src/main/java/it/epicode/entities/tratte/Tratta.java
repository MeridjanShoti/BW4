package it.epicode.entities.tratte;

import it.epicode.entities.mezzi.Mezzo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Tratta {
    @Id
    @GeneratedValue
    private Long id;
    private String partenza;
    private String capolinea;
    private int tempoPercorrenzaPrevisto;
    @OneToMany (mappedBy = "tratta")
    private List<Mezzo> mezzi;
}
