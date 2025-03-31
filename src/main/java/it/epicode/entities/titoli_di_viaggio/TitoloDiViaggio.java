package it.epicode.entities.titoli_di_viaggio;

import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.persone.Utente;
import it.epicode.enums.DurataValidita;
import jakarta.persistence.*;

@Entity
public abstract class TitoloDiViaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataEmissione;
    @ManyToOne
    private Emissione luogoEmissione;


}
