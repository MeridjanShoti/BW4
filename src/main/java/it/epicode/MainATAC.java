package it.epicode;

import it.epicode.dao.*;
import it.epicode.entities.persone.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class MainATAC {
    public static void vaiAPuntoVendita (){
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
    }
    public static void main(String[] args) {
        System.out.println("Sei Utente o amministratore?");
        System.out.println("1. Utente");
        System.out.println("2. Amministratore");
        Scanner scanner = new Scanner(System.in);
        int scelta = scanner.nextInt();
        scanner.nextLine();
        boolean continua = true;
        while(continua){
            switch (scelta) {
                case 1:
                    vaiAPuntoVendita();
                    break;
                case 2:
                    System.out.println("Inserisci la password");
                    String password = scanner.nextLine();
                    if (password.equals("admin")) {
                        System.out.println("Sei un amministratore");
                    } else {
                        System.out.println("Password errata");
                    }
                    break;
                case 0:
                    continua = false;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }

    }
}
