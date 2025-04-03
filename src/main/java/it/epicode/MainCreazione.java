package it.epicode;

import it.epicode.dao.*;
import it.epicode.entities.emissioni.DistributoreAutomatico;
import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.emissioni.Rivenditore;
import it.epicode.entities.mezzi.Autobus;
import it.epicode.entities.mezzi.InterventoManutenzione;
import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.mezzi.Tram;
import it.epicode.entities.persone.Utente;
import it.epicode.entities.titoli_di_viaggio.*;
import it.epicode.entities.tratte.Tratta;
import it.epicode.enums.Attivita;
import it.epicode.enums.DurataValidita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class MainCreazione {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        InterventoManutenzioneDAO interventoManutenzioneDAO = new InterventoManutenzioneDAO(em);

        Utente utente1 = new Utente("Giuseppe", "Verdi", null);
        Utente utente2 = new Utente("Mario", "Rossi", null);
        Utente utente3 = new Utente("Luca", "Bianchi", null);
        Utente utente4 = new Utente("Andrea", "Neri", null);
        Utente utente5 = new Utente("Francesca", "Gialli", null);
        Utente utente6 = new Utente("Sara", "Azzurri", null);
        Utente utente7 = new Utente("Giovanni", "Viola", null);
        Utente utente8 = new Utente("Paolo", "Marroni", null);
        Utente utente9 = new Utente("Valentina", "Rosa", null);
        Utente utente10 = new Utente("Elena", "Blu", null);


        List<Utente> utenti = List.of(utente1, utente2, utente3, utente4, utente5, utente6, utente7, utente8, utente9, utente10);

        Emissione emissione = new Rivenditore("Roma", "Giuseppe");
        Emissione emissione2 = new Rivenditore("Milano", "Mario");
        Emissione emissione3 = new DistributoreAutomatico("Torino", Attivita.ATTIVO);
        Emissione emissione4 = new DistributoreAutomatico("Napoli", Attivita.FUORI_SERVIZIO);

        List<Emissione> emissioni = List.of(emissione, emissione2, emissione3, emissione4);

        TitoloDiViaggio biglietto = new Biglietto(LocalDate.of(2023, 12, 31), emissione3);
        TitoloDiViaggio biglietto2 = new Biglietto(LocalDate.of(2023, 12, 31), emissione2);
        TitoloDiViaggio tessera1 = new Tessera(LocalDate.of(2025, 1, 31), emissione, utente1);
        TitoloDiViaggio tessera2 = new Tessera(LocalDate.of(2025, 1, 31), emissione2,  utente2);
        TitoloDiViaggio tessera3 = new Tessera(LocalDate.of(2025, 1, 31), emissione3,  utente3);
        TitoloDiViaggio tessera4 = new Tessera(LocalDate.of(2025, 1, 31), emissione2,  utente4);
        TitoloDiViaggio tessera5 = new Tessera(LocalDate.of(2025, 1, 31), emissione,  utente5);
        TitoloDiViaggio tessera6 = new Tessera(LocalDate.of(2025, 1, 31), emissione2, utente6);
        TitoloDiViaggio tessera7 = new Tessera(LocalDate.of(2025, 1, 31), emissione3, utente7);
        TitoloDiViaggio tessera8 = new Tessera(LocalDate.of(2025, 1, 31), emissione3,  utente8);
        TitoloDiViaggio tessera9 = new Tessera(LocalDate.of(2025, 1, 31), emissione2,  utente9);
        TitoloDiViaggio tessera10 = new Tessera(LocalDate.of(2023, 12, 31), emissione4, utente10);

        List<TitoloDiViaggio> titoliDiViaggio = List.of(biglietto, biglietto2, tessera1, tessera2, tessera3, tessera4, tessera5, tessera6, tessera7, tessera8, tessera9, tessera10);

        TitoloDiViaggio abbonamento = new Abbonamento(DurataValidita.MENSILE, LocalDate.of(2024, 12, 31), emissione3, (Tessera) tessera1);
        TitoloDiViaggio abbonamento2 = new Abbonamento(DurataValidita.SETTIMANALE, LocalDate.of(2025, 3, 31), emissione2, (Tessera) tessera2);
        TitoloDiViaggio abbonamento3 = new Abbonamento(DurataValidita.MENSILE, LocalDate.of(2025, 3, 31), emissione3, (Tessera) tessera3);
        TitoloDiViaggio abbonamento4 = new Abbonamento(DurataValidita.SETTIMANALE, LocalDate.of(2025, 3, 31), emissione, (Tessera) tessera4);
        TitoloDiViaggio abbonamento5 = new Abbonamento(DurataValidita.MENSILE, LocalDate.of(2025, 3, 31), emissione, (Tessera) tessera5);
        TitoloDiViaggio abbonamento6 = new Abbonamento(DurataValidita.SETTIMANALE, LocalDate.of(2025, 3, 31), emissione2, (Tessera) tessera6);
        TitoloDiViaggio abbonamento7 = new Abbonamento(DurataValidita.MENSILE, LocalDate.of(2025, 3, 31), emissione3, (Tessera) tessera7);
        TitoloDiViaggio abbonamento8 = new Abbonamento(DurataValidita.SETTIMANALE, LocalDate.of(2025, 3, 31), emissione3, (Tessera) tessera8);
        TitoloDiViaggio abbonamento9 = new Abbonamento(DurataValidita.MENSILE, LocalDate.of(2025, 3, 31), emissione, (Tessera) tessera9);

        List<TitoloDiViaggio> titoliDiViaggio2 = List.of(abbonamento, abbonamento2, abbonamento3, abbonamento4, abbonamento5, abbonamento6, abbonamento7, abbonamento8, abbonamento9);

        Tratta tratta = new Tratta( "Roma", "Milano", 60, null);
        Tratta tratta2 = new Tratta("Milano", "Torino", 46, null);
        Tratta tratta3 = new Tratta("Torino", "Roma", 37, null);


        List<Tratta> tratte = List.of(tratta, tratta2, tratta3);


        Mezzo mezzo = new Autobus( tratta);
        Mezzo  mezzo2 = new Autobus( tratta2);
        Mezzo  mezzo3 = new Autobus( tratta);
        Mezzo  mezzo4 = new Autobus( tratta3);
        Mezzo  mezzo5 = new Tram( tratta);
        Mezzo  mezzo6 = new Tram( tratta2);
        Mezzo  mezzo7 = new Tram( tratta);

        List<Mezzo> mezzi = List.of(mezzo, mezzo2, mezzo3, mezzo4, mezzo5, mezzo6, mezzo7);

        InterventoManutenzione interventoManutenzione1 = new InterventoManutenzione();
        InterventoManutenzione  interventoManutenzione2 = new InterventoManutenzione();
        InterventoManutenzione  interventoManutenzione3 = new InterventoManutenzione();
        InterventoManutenzione  interventoManutenzione4 = new InterventoManutenzione();
        InterventoManutenzione  interventoManutenzione5 = new InterventoManutenzione();


        List<InterventoManutenzione> interventi = List.of(interventoManutenzione1, interventoManutenzione2, interventoManutenzione3, interventoManutenzione4, interventoManutenzione5);

        em.getTransaction().begin();
        utenti.forEach(utenteDAO::saveNoTx);
        emissioni.forEach(emissioneDAO::saveNoTx);
        titoliDiViaggio.forEach(titoliDiViaggioDAO::saveNoTx);
        titoliDiViaggio2.forEach(titoliDiViaggioDAO::saveNoTx);
        tratte.forEach(trattaDAO::saveNoTx);
        mezzi.forEach(mezzoDAO::saveNoTx);
        interventi.forEach(interventoManutenzioneDAO::saveNoTx);
        em.getTransaction().commit();

        interventoManutenzioneDAO.iniziaManutenzione(interventoManutenzione1, LocalDate.of(2024, 12, 1), mezzo2);
        interventoManutenzioneDAO.finisciManutenzione(interventoManutenzione1, LocalDate.of(2024, 12, 12));
        interventoManutenzioneDAO.iniziaManutenzione(interventoManutenzione2, LocalDate.of(2025, 3, 5), mezzo3);
        interventoManutenzioneDAO.finisciManutenzione(interventoManutenzione2, LocalDate.of(2025, 3, 10));
        interventoManutenzioneDAO.iniziaManutenzione(interventoManutenzione3, LocalDate.of(2025, 12, 5), mezzo4);
        interventoManutenzioneDAO.finisciManutenzione(interventoManutenzione3, LocalDate.of(2025, 12, 6));
        utente1.setTessera((Tessera) tessera1);
        utente2.setTessera((Tessera) tessera2);
        utente3.setTessera((Tessera) tessera3);
        utente4.setTessera((Tessera) tessera4);
        utente5.setTessera((Tessera) tessera5);
        utente6.setTessera((Tessera) tessera6);
        utente7.setTessera((Tessera) tessera7);
        utente8.setTessera((Tessera) tessera8);
        utente9.setTessera((Tessera) tessera9);
        utente10.setTessera((Tessera) tessera10);
        em.getTransaction().begin();
        interventi.forEach(interventoManutenzioneDAO::updateNoTx);
        mezzi.forEach(mezzoDAO::updateNoTx);
        utenti.forEach(utenteDAO::updateNoTx);
        emissioni.forEach(emissioneDAO::updateNoTx);
        titoliDiViaggio.forEach(titoliDiViaggioDAO::updateNoTx);
        titoliDiViaggio2.forEach(titoliDiViaggioDAO::updateNoTx);
        tratte.forEach(trattaDAO::updateNoTx);
        em.getTransaction().commit();


    }
}
