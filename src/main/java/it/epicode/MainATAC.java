package it.epicode;

import it.epicode.dao.*;
import it.epicode.entities.emissioni.DistributoreAutomatico;
import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.emissioni.Rivenditore;
import it.epicode.entities.mezzi.Autobus;
import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.mezzi.Tram;
import it.epicode.entities.persone.Amministratore;
import it.epicode.entities.persone.Utente;
import it.epicode.entities.titoli_di_viaggio.Abbonamento;
import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import it.epicode.entities.titoli_di_viaggio.TitoloDiViaggio;
import it.epicode.entities.tratte.Tratta;
import it.epicode.enums.Attivita;
import it.epicode.enums.DurataValidita;
import it.epicode.enums.InServizio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

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

        List<Utente> utenti = List.of(utente, utente2, utente3, utente4, utente5, utente6, utente7, utente8, utente9, utente10);

        Emissione emissione = new Rivenditore("Roma", "Giuseppe");
        Emissione emissione2 = new Rivenditore("Milano", "Mario");
        Emissione emissione3 = new DistributoreAutomatico("Torino", Attivita.ATTIVO);
        Emissione emissione4 = new DistributoreAutomatico("Roma", Attivita.FUORI_SERVIZIO);

        List<Emissione> emissioni = List.of(emissione, emissione2, emissione3, emissione4);

        TitoloDiViaggio biglietto = new Biglietto(LocalDate.of(2023, 12, 31), emissione3);
        TitoloDiViaggio biglietto2 = new Biglietto(LocalDate.of(2023, 12, 31), emissione2);
        TitoloDiViaggio tessera = new Tessera(LocalDate.of(2025, 1, 31), emissione, utente);
        TitoloDiViaggio tessera2 = new Tessera(LocalDate.of(2025, 1, 31), emissione2, utente2);
        TitoloDiViaggio tessera3 = new Tessera(LocalDate.of(2025, 1, 31), emissione3, utente3);
        TitoloDiViaggio tessera4 = new Tessera(LocalDate.of(2025, 1, 31), emissione2, utente4);
        TitoloDiViaggio tessera5 = new Tessera(LocalDate.of(2025, 1, 31), emissione, utente5);
        TitoloDiViaggio tessera6 = new Tessera(LocalDate.of(2025, 1, 31), emissione2, utente6);
        TitoloDiViaggio tessera7 = new Tessera(LocalDate.of(2025, 1, 31), emissione3, utente7);
        TitoloDiViaggio tessera8 = new Tessera(LocalDate.of(2025, 1, 31), emissione3, utente8);
        TitoloDiViaggio tessera9 = new Tessera(LocalDate.of(2025, 1, 31), emissione2, utente9);
        TitoloDiViaggio tessera10 = new Tessera(LocalDate.of(2023, 12, 31), emissione4, utente10);

        List<TitoloDiViaggio> titoliDiViaggio = List.of(biglietto, biglietto2, tessera, tessera2, tessera3, tessera4, tessera5, tessera6, tessera7, tessera8, tessera9, tessera10);

        TitoloDiViaggio abbonamento = new Abbonamento(DurataValidita.MENSILE, LocalDate.of(2024, 12, 31), emissione3, (Tessera) tessera);
        TitoloDiViaggio abbonamento2 = new Abbonamento(DurataValidita.SETTIMANALE, LocalDate.of(2025, 3, 31), emissione2, (Tessera) tessera2);
        TitoloDiViaggio abbonamento3 = new Abbonamento(DurataValidita.MENSILE, LocalDate.of(2025, 3, 31), emissione3, (Tessera) tessera3);
        TitoloDiViaggio abbonamento4 = new Abbonamento(DurataValidita.SETTIMANALE, LocalDate.of(2025, 3, 31), emissione, (Tessera) tessera4);
        TitoloDiViaggio abbonamento5 = new Abbonamento(DurataValidita.MENSILE, LocalDate.of(2025, 3, 31), emissione, (Tessera) tessera5);
        TitoloDiViaggio abbonamento6 = new Abbonamento(DurataValidita.SETTIMANALE, LocalDate.of(2025, 3, 31), emissione2, (Tessera) tessera6);
        TitoloDiViaggio abbonamento7 = new Abbonamento(DurataValidita.MENSILE, LocalDate.of(2025, 3, 31), emissione3, (Tessera) tessera7);
        TitoloDiViaggio abbonamento8 = new Abbonamento(DurataValidita.SETTIMANALE, LocalDate.of(2025, 3, 31), emissione3, (Tessera) tessera8);
        TitoloDiViaggio abbonamento9 = new Abbonamento(DurataValidita.MENSILE, LocalDate.of(2025, 3, 31), emissione, (Tessera) tessera9);

        List<TitoloDiViaggio> titoliDiViaggio2 = List.of(abbonamento, abbonamento2, abbonamento3, abbonamento4, abbonamento5, abbonamento6, abbonamento7, abbonamento8, abbonamento9);

        Tratta tratta = new Tratta( "Roma", "Milano", 200, null);
        Tratta tratta2 = new Tratta("Milano", "Torino", 300, null);

        List<Tratta> tratte = List.of(tratta, tratta2);


        Mezzo  mezzo = new Autobus(InServizio.IN_SERVIZIO, tratta);
        Mezzo  mezzo2 = new Autobus(InServizio.IN_SERVIZIO, tratta2);
        Mezzo  mezzo3 = new Autobus(InServizio.IN_SERVIZIO, tratta);
        Mezzo  mezzo4 = new Autobus(InServizio.IN_MANUTENZIONE, tratta2);
        Mezzo  mezzo5 = new Tram(InServizio.IN_SERVIZIO, tratta);
        Mezzo  mezzo6 = new Tram(InServizio.IN_MANUTENZIONE, tratta2);
        Mezzo  mezzo7 = new Tram(InServizio.IN_SERVIZIO, tratta);

        List<Mezzo> mezzi = List.of(mezzo, mezzo2, mezzo3, mezzo4, mezzo5, mezzo6, mezzo7);

        em.getTransaction().begin();
        utenti.forEach(personeDAO::saveNoTx);
        emissioni.forEach(emissioneDAO::saveNoTx);
        titoliDiViaggio.forEach(titoliDiViaggioDAO::saveNoTx);
        titoliDiViaggio2.forEach(titoliDiViaggioDAO::saveNoTx);
        tratte.forEach(trattaDAO::saveNoTx);
        mezzi.forEach(mezzoDAO::saveNoTx);
        em.getTransaction().commit();
    }
}
