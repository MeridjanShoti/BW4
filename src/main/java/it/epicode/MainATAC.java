package it.epicode;

import it.epicode.dao.*;
import it.epicode.entities.emissioni.Emissione;
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
        emissioneDAO.getById(1L);
    }
}
