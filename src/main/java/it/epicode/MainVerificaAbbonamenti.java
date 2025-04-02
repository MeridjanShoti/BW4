package it.epicode;

import it.epicode.dao.*;
import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import it.epicode.entities.titoli_di_viaggio.TitoloDiViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Arrays;

public class MainVerificaAbbonamenti {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        TitoloDiViaggio  tessera = titoliDiViaggioDAO.getById(3L);
        System.out.println("controllo abbonamento tessera 3: " + titoliDiViaggioDAO.checkAbbonamentoByTessera((Tessera) tessera));
        TitoloDiViaggio tessera2 = titoliDiViaggioDAO.getById(4L);
        System.out.println("controllo abbonamento tessera 4: " + titoliDiViaggioDAO.checkAbbonamentoByTessera((Tessera) tessera2));

        Object[] conteggio = titoliDiViaggioDAO.countByPVEData(LocalDate.of(2023, 11, 1), LocalDate.of(2026, 11, 30));

    }
}
