package it.epicode;

import it.epicode.dao.*;
import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.persone.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
        Utente utente10 = new Utente("Elena", "Blu", null);



    }
}
