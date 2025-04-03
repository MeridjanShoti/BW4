package it.epicode;

import it.epicode.dao.*;
import it.epicode.entities.mezzi.Autobus;
import it.epicode.entities.mezzi.InterventoManutenzione;
import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.mezzi.Tram;
import it.epicode.entities.persone.Utente;
import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.entities.titoli_di_viaggio.ConteggioByPuntoVenditaEData;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import it.epicode.exceptions.ErroreDiInserimentoException;
import it.epicode.exceptions.TipoTitoloDiViaggioException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainATAC {
    public static void autista() {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        System.out.println("Salve signor autista, inserisca l'id del mezzo che vuole far partire");
        Long idMezzo = 0L;
        try {
            idMezzo = scanner.nextLong();
        } catch (Exception e) {
            System.out.println("dovevi inserire un numero");
        } finally {
            scanner.nextLine();
        }
        Mezzo mezzo = null;
        try {
            if (idMezzo == 0L) {
                throw new ErroreDiInserimentoException("mezzo non trovato");
            }
            mezzo = mezzoDAO.getById(idMezzo);
            mezzo.parti();
            mezzoDAO.update(mezzo);
        } catch (ErroreDiInserimentoException e) {
            System.out.println(e.getMessage());
        }

        if (mezzo instanceof Autobus) {
            System.out.println("brum brum, l'autobus sta partendo");
        } else if (mezzo instanceof Tram) {
            System.out.println("din din, il tram sta partendo");
        } else {
            System.out.println("non so che tipo di mezzo è ma ti assicuro che sta partendo");
        }
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
            System.out.println("0. Uscire");
            System.out.print("Scegli (1, 2, 3, 0): ");
            int scelta = 50;
            try {
                scelta = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("dovevi inserire un numero!");
            } finally {
                scanner.nextLine();
            }

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
                    Long idUtente = null;
                    try {
                        idUtente = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("Inserisci l'anno corrente");
                    int anno = 0;
                    try {
                        anno = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("Inserisci il mese corrente");
                    int mese = 0;
                    try {
                        mese = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("Inserisci il giorno corrente");
                    int giorno = 0;
                    try {
                        giorno = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    LocalDate dataEmissione = null;
                    Utente utente = null;
                    try {
                        if (anno == 0 || mese == 0 || giorno == 0) {
                            throw new ErroreDiInserimentoException("data non valida");
                        }
                        dataEmissione = LocalDate.of(anno, mese, giorno);
                        utente = em.find(Utente.class, idUtente);
                    } catch (ErroreDiInserimentoException e) {
                        System.out.println(e.getMessage());
                    }

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
            int scelta = 50;
            try {
                scelta = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("dovevi inserire un numero");
            } finally {
                scanner.nextLine();
            }

            switch (scelta) {
                case 1:
                    vaiAPuntoVendita();
                    break;
                case 2:
                    System.out.print("Inserisci l'ID del biglietto: ");
                    Long idBiglietto = null;
                    try {
                        idBiglietto = scanner.nextLong();
                    } catch (Exception e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("inserisci l'id del mezzo da prendere");
                    long idMezzo = 0;
                    try {
                        idMezzo = scanner.nextLong();
                    } catch (Exception e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    try {
                        if (!(titoliDiViaggioDAO.getById(idBiglietto) instanceof Biglietto)) {
                            throw new TipoTitoloDiViaggioException("il titolo di viaggio non è un biglietto");
                        }
                        if (idBiglietto == null || idMezzo == 0) {
                            throw new ErroreDiInserimentoException("Errore nell'inserimento. Biglietto non timbrato.");
                        }
                        Mezzo mezzo = mezzoDAO.getById(idMezzo);
                        Biglietto biglietto = (Biglietto) titoliDiViaggioDAO.getById(idBiglietto);
                        mezzoDAO.timbraBigliettoDaId(biglietto, mezzo);
                        titoliDiViaggioDAO.update(biglietto);
                        mezzoDAO.update(mezzo);
                    } catch (TipoTitoloDiViaggioException e) {
                        System.out.println(e.getMessage());
                    } catch (ErroreDiInserimentoException e) {
                        System.out.println(e.getMessage());
                    }
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        System.out.println("GESTISCI PARCO MEZZI");
        boolean continua = true;
        while (continua) {
            System.out.println("Scegli un'opzione:");
            System.out.println("1. Visualizza informazioni percorso di un mezzo");
            System.out.println("2. metti in manutenzione un mezzo");
            System.out.println("3. rimetti in servizio un mezzo in manutenzione");
            System.out.println("4. Visualizza informazioni mezzo");
            System.out.println("5. Visualizza informazioni manutenzione");
            System.out.println("0. Esci");
            System.out.print("Scegli (1, 2, 3, 0): ");

            Scanner scanner = new Scanner(System.in);
            int scelta = 50;
            try {
                scelta = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("dovevi inserire un numero");
            } finally {
                scanner.nextLine();
            }

            switch (scelta) {
                case 1:
                    System.out.println("inserisci l'id del mezzo");
                    long idMezzo = 0L;
                    try {
                        idMezzo = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    try {
                        if (idMezzo == 0L) {
                            throw new ErroreDiInserimentoException("Errore nell'inserimento");
                        }
                        Mezzo mezzo = mezzoDAO.getById(idMezzo);
                        mezzo.dettagliPercorsiMezzo();
                    } catch (ErroreDiInserimentoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("inserisci l'id del mezzo");
                    long idMezzo2 = 0L;
                    try {
                        idMezzo2 = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    Mezzo mezzo2 = null;
                    if (idMezzo2 != 0L) {
                        mezzo2 = mezzoDAO.getById(idMezzo2);
                    }
                    System.out.println("inserisci la data di inizio manutenzione");
                    System.out.println("anno");
                    int anno = 0;
                    try {
                        anno = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("mese");
                    int mese = 0;
                    try {
                        mese = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("giorno");
                    int giorno = 0;
                    try {
                        giorno = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    try {
                        if (anno == 0 || mese == 0 || giorno == 0 || mezzo2 == null) {
                            throw new ErroreDiInserimentoException("data non valida");
                        }
                        if (idMezzo2 == 0L) {
                            throw new ErroreDiInserimentoException("Errore nell'inserimento");
                        }
                        LocalDate dataInizioManutenzione = LocalDate.of(anno, mese, giorno);
                        InterventoManutenzione interventoManutenzione = new InterventoManutenzione();
                        interventoManutenzione.setMezzoInManutenzione(mezzo2);
                        interventoManutenzione.setInizioManutenzione(dataInizioManutenzione);
                        em.getTransaction().begin();
                        em.persist(interventoManutenzione);
                        mezzoDAO.updateNoTx(mezzo2);
                        em.getTransaction().commit();
                    } catch (ErroreDiInserimentoException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 3:
                    System.out.println("inserisci l'id della manutenzione da terminare");
                    long idIntervento = 0L;
                    try {
                        idIntervento = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    InterventoManutenzione interventoManutenzioneDaTerminare = null;
                    if (idIntervento != 0L) {
                        interventoManutenzioneDaTerminare = em.find(InterventoManutenzione.class, idIntervento);
                    }
                    System.out.println("inserisci la data di fine manutenzione");
                    System.out.println("anno");
                    int anno2 = 0;
                    try {
                        anno2 = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("mese");
                    int mese2 = 0;
                    try {
                        mese2 = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("giorno");
                    int giorno2 = 0;
                    try {
                        giorno2 = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    try {
                        if (anno2 == 0 || mese2 == 0 || giorno2 == 0 || idIntervento == 0L) {
                            throw new ErroreDiInserimentoException("data non valida");
                        }
                        LocalDate dataFineManutenzione = LocalDate.of(anno2, mese2, giorno2);
                        interventoManutenzioneDaTerminare.setFineManutenzione(dataFineManutenzione);
                        em.getTransaction().begin();
                        em.merge(interventoManutenzioneDaTerminare);
                        mezzoDAO.updateNoTx(interventoManutenzioneDaTerminare.getMezzoInManutenzione());
                        em.getTransaction().commit();
                    } catch (ErroreDiInserimentoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("inserisci l'id del mezzo");
                    long idMezzo3 = 0L;
                    try {
                        idMezzo3 = scanner.nextLong();
                    } catch (Exception e) {
                        System.out.println("dovevi inserire un numero");
                    } finally {
                        scanner.nextLine();
                    }
                    try {
                        if (idMezzo3 == 0L) {
                            throw new ErroreDiInserimentoException("Errore nell'inserimento");
                        }
                        Mezzo mezzo3 = mezzoDAO.getById(idMezzo3);
                        System.out.println(mezzo3.toString());
                    } catch (ErroreDiInserimentoException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:
                    System.out.println("visualizzaMezziInManutenzione();");
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

    public static void gestisciVendite() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ATAC");
        EntityManager em = emf.createEntityManager();
        EmissioneDAO emissioneDAO = new EmissioneDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TitoliDiViaggioDAO titoliDiViaggioDAO = new TitoliDiViaggioDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        Scanner scanner = new Scanner(System.in);
        System.out.println("GESTIONE VENDITE");
        System.out.println("inserisci la prima data");
        System.out.println("anno");
        int anno1 = 0;
        try {
            anno1 = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Dovevi inserire un numero!");
        } finally {
            scanner.nextLine();
        }
        System.out.println("mese");
        int mese1 = 0;
        try {
            mese1 = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println();
        } finally {
            scanner.nextLine();
        }
        System.out.println("giorno");
        int giorno1 = 0;
        try {
            giorno1 = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Dovevi inserire un numero!");
        } finally {
            scanner.nextLine();
        }
        System.out.println("inserisci la seconda data");
        System.out.println("anno");
        int anno2 = 0;
        try {
            anno2 = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Dovevi inserire un numero!");
        } finally {
            scanner.nextLine();
        }
        System.out.println("mese");
        int mese2 = 0;
        try {
            mese2 = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Dovevi inserire un numero!");
        } finally {
            scanner.nextLine();
        }
        System.out.println("giorno");
        int giorno2 = 0;
        try {
            giorno2 = scanner.nextInt();
        } catch (Exception e) {
            System.out.println();
        } finally {
            scanner.nextLine();
        }
        try {
            if (anno1 == 0 || mese1 == 0 || giorno1 == 0 || anno2 == 0 || mese2 == 0 || giorno2 == 0) {
                throw new ErroreDiInserimentoException("Errore nell'inserimento");
            }
            LocalDate data1 = LocalDate.of(anno1, mese1, giorno1);
            LocalDate data2 = LocalDate.of(anno2, mese2, giorno2);
            List<ConteggioByPuntoVenditaEData> conteggio = titoliDiViaggioDAO.countByPVEData(data1, data2);
            System.out.println("il conteggio per punto vendita tra " + data1 + " e " + data2 + " è: \n" + conteggio.toString());
        } catch (ErroreDiInserimentoException e) {
            System.out.println(e.getMessage());
        }
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
            System.out.println("Salve signor controllore, scegli un'opzione:");
            System.out.println("1. Controlla biglietto");
            System.out.println("2. Controlla validità abbonamento");
            System.out.println("3. Conta biglietti timbrati da un mezzo");
            System.out.println("4. Conta biglietti timbrati in un certo lasso di tempo");
            System.out.println("0. Uscire");
            Scanner scanner = new Scanner(System.in);
            int scelta = 50;
            try {
                scelta = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println();
            } finally {
                scanner.nextLine();
            }
            switch (scelta) {
                case 1:
                    System.out.println("inserisci l'id del biglietto da controllare");
                    long idBiglietto = 0L;
                    try {
                        idBiglietto = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("dovevi inserire un numero!");
                    } finally {
                        scanner.nextLine();
                    }
                    try {
                        if (!(titoliDiViaggioDAO.getById(idBiglietto) instanceof Biglietto)) {
                            throw new TipoTitoloDiViaggioException("il titolo di viaggio selezionato non è un biglietto");
                        }
                        if (idBiglietto == 0L) {
                            throw new ErroreDiInserimentoException("Errore nell'inserimento");
                        }
                        Biglietto biglietto = (Biglietto) titoliDiViaggioDAO.getById(idBiglietto);
                        if (biglietto.isValidita()) {
                            System.out.println("biglietto valido");
                        } else {
                            System.out.println("biglietto non valido");
                        }
                    } catch (TipoTitoloDiViaggioException e) {
                        System.out.println(e.getMessage());
                    } catch (ErroreDiInserimentoException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    System.out.println("inserisci l'id della tessera da controllare");
                    long idTessera = 0L;
                    try {
                        idTessera = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("Dovevi inserire un numero!");
                    } finally {
                        scanner.nextLine();
                    }
                    try {
                        if (!(titoliDiViaggioDAO.getById(idTessera) instanceof Tessera)) {
                            throw new TipoTitoloDiViaggioException("il titolo di viaggio selezionato non è una tessera");
                        }
                        if (idTessera == 0L) {
                            throw new ErroreDiInserimentoException("Errore nell'inserimento");
                        }
                        Tessera tessera = (Tessera) titoliDiViaggioDAO.getById(idTessera);
                        if (titoliDiViaggioDAO.checkAbbonamentoByTessera(tessera)) {
                            System.out.println("titolo di viaggio valido");
                        } else {
                            System.out.println("titolo di viaggio non valido");
                        }
                    } catch (TipoTitoloDiViaggioException e) {
                        System.out.println(e.getMessage());
                    } catch (ErroreDiInserimentoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("inserisci l'id del mezzo da controllare");
                    long idMezzo = 0L;
                    try {
                        idMezzo = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println("Dovevi inserire un numero!");
                    } finally {
                        scanner.nextLine();
                    }
                    try {
                        if (idMezzo == 0L) {
                            throw new ErroreDiInserimentoException("Errore nell'inserimento");
                        }
                        Mezzo mezzo = mezzoDAO.getById(idMezzo);
                        long numeroBiglietti = titoliDiViaggioDAO.countBigliettiByMezzo(mezzo);
                        System.out.println("il mezzo " + mezzo.getId() + " ha timbrato " + numeroBiglietti + " biglietti");
                    } catch (ErroreDiInserimentoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:

                    System.out.println("inserisci l'anno della prima data");
                    int anno = 0;
                    try {
                        anno = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Dovevi inserire un numero!");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("inserisci il mese della prima data");
                    int mese = 0;
                    try {
                        mese = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Dovevi inserire un numero!");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("inserisci il giorno della prima data");
                    int giorno = 0;
                    try {
                        giorno = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un numero!");
                    } finally {
                        scanner.nextLine();
                    }
                    LocalDate data1 = LocalDate.of(anno, mese, giorno);
                    System.out.println("inserisci l'anno della seconda data");
                    int anno2 = 0;
                    try {
                        anno2 = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un numero!");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("inserisci il mese della seconda data");
                    int mese2 = 0;
                    try {
                        mese2 = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un numero!");
                    } finally {
                        scanner.nextLine();
                    }
                    System.out.println("inserisci il giorno della seconda data");
                    int giorno2 = 0;
                    try {
                        giorno2 = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un numero!");
                    } finally {
                        scanner.nextLine();
                    }
                    try {
                        if (anno == 0 || mese == 0 || giorno == 0 || anno2 == 0 || mese2 == 0 || giorno2 == 0) {
                            throw new ErroreDiInserimentoException("Errore nell'inserimento");
                        }
                        LocalDate data2 = LocalDate.of(anno2, mese2, giorno2);
                        long numeroBiglietti2 = titoliDiViaggioDAO.findBigliettoByTempo(data1, data2);
                        System.out.println("tra le date " + data1 + " e " + data2 + " sono stati timbrati " + numeroBiglietti2 + " biglietti");
                    } catch (ErroreDiInserimentoException e) {
                        System.out.println(e.getMessage());
                    }

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
            int scelta = 50;
            try {
                scelta = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Dovevi inserire un numero!");
            } finally {
                scanner.nextLine();
            }

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
            int scelta = 50;
            try {
                scelta = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Dovevi inserire un numero!");
            } finally {
                scanner.nextLine();
            }
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
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }

    }

}
