package it.epicode;

import it.epicode.dao.*;
import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.persone.Utente;
import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.entities.titoli_di_viaggio.ConteggioByPuntoVenditaEData;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainATAC {
    public static void autista(){
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        System.out.println("Salve signor autista, inserisca l'id del mezzo che vuole far partire");
        Long idMezzo = scanner.nextLong();
        scanner.nextLine();
        Mezzo mezzo = mezzoDAO.getById(idMezzo);
        mezzo.parti();
        mezzoDAO.update(mezzo);
        em.close();
        emf.close();
    }
    public static void vaiAPuntoVendita() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);

        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            System.out.println("\nScegli un'opzione:");
            System.out.println("1. Crea nuovo utente");
            System.out.println("2. Crea tessera per utente");
            System.out.println("3. Acquista biglietto o abbonamento");
            System.out.println("4. Uscire");
            System.out.print("Scegli (1, 2, 3, 0): ");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci il nome dell'utente: ");
                    String nome = scanner.next();
                    System.out.print("Inserisci il cognome dell'utente: ");
                    String cognome = scanner.next();
                    utenteDAO.creaUtente(nome, cognome);
                    break;
                case 2:
                    System.out.print("Inserisci l'ID dell'utente per creare la tessera: ");
                    Long idUtente = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Inserisci l'anno corrente");
                    int anno = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci il mese corrente");
                    int mese = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci il giorno corrente");
                    int giorno = scanner.nextInt();
                    scanner.nextLine();
                    LocalDate dataEmissione = LocalDate.of(anno, mese, giorno);
                    Utente utente = em.find(Utente.class, idUtente);
                    if (utente != null) {
                        utenteDAO.creaTessera(utente, dataEmissione);
                    } else {
                        System.out.println("Utente non trovato.");
                    }
                    break;
                case 3:
                    emissioneDAO.vendiTitoloDiViaggio();
                    break;

                case 0:
                    continua = false;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
        em.close();
        emf.close();
    }

    public static void operazioniUtente() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);

        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            System.out.println("\nScegli un'opzione:");
            System.out.println("1. Acquista Titoli di viaggio");
            System.out.println("2. Timbra Biglietto");
            System.out.println("0. Uscire");
            System.out.print("Scegli (1, 2, 0): ");
            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    vaiAPuntoVendita();
                    break;
                case 2:
                    System.out.print("Inserisci l'ID del biglietto: ");
                    Long idBiglietto = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("inserisci l'id del mezzo da prendere");
                    long idMezzo = scanner.nextLong();
                    scanner.nextLine();
                    Mezzo mezzo = mezzoDAO.getById(idMezzo);
                    Biglietto biglietto = (Biglietto) titoliDiViaggioDAO.getById(idBiglietto);
                    mezzoDAO.timbraBigliettoDaId(biglietto, mezzo);
                    titoliDiViaggioDAO.update(biglietto);
                    mezzoDAO.update(mezzo);
                    break;
                case 0:
                    continua = false;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
        em.close();
        emf.close();
    }

    public static void gestisciParcoMezzi() {
        System.out.println("GESTISCI PARCO MEZZI");
    }

    public static void gestisciVendite() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci la prima data");
        System.out.println("anno");
        int anno1 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("mese");
        int mese1 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("giorno");
        int giorno1 = scanner.nextInt();
        scanner.nextLine();
        LocalDate data1 = LocalDate.of(anno1, mese1, giorno1);
        System.out.println("inserisci la seconda data");
        System.out.println("anno");
        int anno2 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("mese");
        int mese2 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("giorno");
        int giorno2 = scanner.nextInt();
        scanner.nextLine();
        LocalDate data2 = LocalDate.of(anno2, mese2, giorno2);
        List<ConteggioByPuntoVenditaEData> conteggio = titoliDiViaggioDAO.countByPVEData(data1, data2);
        System.out.println("il conteggio per punto vendita tra "+ data1 + " e " + data2 + " è: \n"+ conteggio.toString());
        em.close();
        emf.close();
    }

    public static void controllore() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);

        boolean continua = true;
        while (continua) {
            System.out.println("Scegli un'opzione:");
            System.out.println("1. Controlla biglietto");
            System.out.println("2. Controlla validità abbonamento");
            System.out.println("3. Conta biglietti timbrati da un mezzo");
            System.out.println("4. Conta biglietti timbrati in un certo lasso di tempo");
            System.out.println("0. Uscire");
            Scanner scanner = new Scanner(System.in);
            int scelta = scanner.nextInt();
            scanner.nextLine();
            switch (scelta) {
                case 1:
                    System.out.println("inserisci l'id del biglietto da controllare");
                    long idBiglietto = scanner.nextLong();
                    scanner.nextLine();
                    Biglietto biglietto = (Biglietto) titoliDiViaggioDAO.getById(idBiglietto);
                    if (biglietto.isValidita()) {
                        System.out.println("biglietto valido");
                    } else {
                        System.out.println("biglietto non valido");
                    }

                    break;
                case 2:
                    System.out.println("inserisci l'id della tessera da controllare");
                    long idTessera = scanner.nextLong();
                    scanner.nextLine();
                    Tessera tessera = (Tessera) titoliDiViaggioDAO.getById(idTessera);
                    if(titoliDiViaggioDAO.checkAbbonamentoByTessera(tessera)){
                        System.out.println("titolo di viaggio valido");
                    } else {
                        System.out.println("titolo di viaggio non valido");
                    }
                    break;
                case 3:
                    System.out.println("inserisci l'id del mezzo da controllare");
                    long idMezzo = scanner.nextLong();
                    scanner.nextLine();
                    Mezzo mezzo = mezzoDAO.getById(idMezzo);
                    long numeroBiglietti = titoliDiViaggioDAO.countBigliettiByMezzo(mezzo);
                    System.out.println("il mezzo " + mezzo.getId() + " ha timbrato " + numeroBiglietti + " biglietti");
                    break;
                case 4:

                    System.out.println("inserisci l'anno della prima data");
                    int anno = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("inserisci il mese della prima data");
                    int mese = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("inserisci il giorno della prima data");
                    int giorno = scanner.nextInt();
                    scanner.nextLine();
                    LocalDate data1 = LocalDate.of(anno, mese, giorno);
                    System.out.println("inserisci l'anno della seconda data");
                    int anno2 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("inserisci il mese della seconda data");
                    int mese2 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("inserisci il giorno della seconda data");
                    int giorno2 = scanner.nextInt();
                    scanner.nextLine();
                    LocalDate data2 = LocalDate.of(anno2, mese2, giorno2);
                    long numeroBiglietti2 = titoliDiViaggioDAO.findBigliettoByTempo(data1, data2);
                    System.out.println("tra le date " + data1 + " e " + data2 + " sono stati timbrati " + numeroBiglietti2 + " biglietti");

                case 0:
                    continua = false;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }
        em.close();
        emf.close();
    }

    public static void vaiAPuntoControllo() {
        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            System.out.println("\nScegli un'opzione:");
            System.out.println("1. Controllore");
            System.out.println("2. Gestione parco mezzi");
            System.out.println("3. Gestisci vendite");
            System.out.println("0. Uscire");
            System.out.print("Scegli (1, 2, 3, 0): ");
            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    controllore();
                    break;
                case 2:
                    gestisciParcoMezzi();
                    break;
                case 3:
                    gestisciVendite();
                    break;
                case 0:
                    continua = false;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    public static void main(String[] args) {

        boolean continua = true;
        while (continua) {
            System.out.println("Sei Utente o amministratore?");
            System.out.println("1. Utente");
            System.out.println("2. Amministratore");
            System.out.println("3. Autista");
            System.out.println("0. Esci");
            Scanner scanner = new Scanner(System.in);
            int scelta = scanner.nextInt();
            scanner.nextLine();
            switch (scelta) {
                case 1:
                    operazioniUtente();
                    break;
                case 2:
                    System.out.println("Inserisci la password");
                    String password = scanner.nextLine();
                    if (password.equals("admin")) {
                        System.out.println("Sei un amministratore");
                        vaiAPuntoControllo();
                        continua = false;
                    } else {
                        System.out.println("Password errata");
                    }
                    break;
                    case 3:
                        autista();
                case 0:
                    continua = false;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }

    }

}
