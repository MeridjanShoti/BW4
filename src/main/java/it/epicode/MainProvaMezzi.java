package it.epicode;

import it.epicode.dao.EmissioneDAO;
import it.epicode.dao.MezzoDAO;
import it.epicode.dao.TitoliDiViaggioDAO;
import it.epicode.dao.TrattaDAO;
import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.emissioni.Rivenditore;
import it.epicode.entities.mezzi.Autobus;
import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.entities.titoli_di_viaggio.TitoloDiViaggio;
import it.epicode.entities.tratte.Tratta;
import it.epicode.enums.InServizio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class MainProvaMezzi {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        Tratta tratta = new Tratta("Roma", "Milano", 30, null);
        trattaDAO.save(tratta);
        Autobus autobus = new Autobus(tratta);
        mezzoDAO.save(autobus);
        Emissione emissione = new Rivenditore("Roma", "Mario Sburroni");
        emissioneDAO.save(emissione);
        TitoloDiViaggio biglietto = new Biglietto(LocalDate.now(), emissione);
        TitoloDiViaggio  biglietto2 = new Biglietto(LocalDate.now(), emissione);
        em.getTransaction().begin();
        em.persist(biglietto);
        em.persist(biglietto2);
        em.getTransaction().commit();
        
    }
}