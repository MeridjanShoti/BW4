package it.epicode;

import it.epicode.dao.*;
import it.epicode.entities.emissioni.DistributoreAutomatico;
import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.emissioni.Rivenditore;
import it.epicode.entities.persone.Amministratore;
import it.epicode.entities.persone.Utente;
import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import it.epicode.entities.titoli_di_viaggio.TitoloDiViaggio;
import it.epicode.enums.Attivita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class MainATAC {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        PersoneDAO personeDAO = new PersoneDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);

        Utente utente = new Utente("Giuseppe", "Verdi", null);
        Utente utente2 = new Utente("Mario", "Rossi", null);
        Utente utente3 = new Utente("Luca", "Bianchi", null);
        Utente utente4 = new Utente("Andrea", "Neri", null);
        Utente utente5 = new Utente("Francesca", "Gialli", null);
        Utente utente6 = new Utente("Sara", "Azzurri", null);
        Utente utente7 = new Utente("Giovanni", "Viola", null);
        Utente utente8 = new Utente("Paolo", "Marroni", null);
        Utente utente9 = new Utente("Valentina", "Rosa", null);
        Utente utente10 = new Amministratore("Elena", "Blu", null);

        Emissione emissione = new Rivenditore("Roma", "Giuseppe");
        Emissione emissione2 = new Rivenditore("Milano", "Mario");
        Emissione emissione3 = new DistributoreAutomatico("Torino", Attivita.ATTIVO);
        Emissione emissione4 = new DistributoreAutomatico("Roma", Attivita.FUORI_SERVIZIO);
        TitoloDiViaggio biglietto = new Biglietto(LocalDate.of(2023, 12, 31), emissione3);
        TitoloDiViaggio biglietto2 = new Biglietto(LocalDate.of(2023, 12, 31), emissione3);


        Tessera tessera = new Tessera(LocalDate.of(2023, 12, 31), , utente);


    }
}
