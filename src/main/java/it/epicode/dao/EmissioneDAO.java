package it.epicode.dao;

import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.titoli_di_viaggio.Abbonamento;
import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import it.epicode.entities.titoli_di_viaggio.TitoloDiViaggio;
import it.epicode.enums.DurataValidita;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Scanner;

public class EmissioneDAO {
    private static EntityManager em;
    private static UtenteDAO utenteDAO = new UtenteDAO(em);
    private static TitoliDiViaggioDAO tdvDAO = new TitoliDiViaggioDAO(em);

    public EmissioneDAO(EntityManager em) {
        this.em = em;
    }
    public void save(Emissione emissione) {
        try {
            em.getTransaction().begin();
            em.persist(emissione);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
    public Emissione getById(Long id) {
        return em.find(Emissione.class, id);
    }
    public void delete(Emissione emissione) {
        try {
            em.getTransaction().begin();
            em.remove(emissione);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
    public void update(Emissione emissione) {
        try {
            em.getTransaction().begin();
            em.merge(emissione);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
    public void saveNoTx(Emissione emissione) {
        em.persist(emissione);
    }
    public void deleteNoTx(Emissione emissione) {
        em.remove(emissione);
    }
    public void updateNoTx(Emissione emissione) {
        em.merge(emissione);
    }

    public void vendiTitoloDiViaggio() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleziona l'operazione da effettuare: ");
        System.out.println("1. Acquista un abbonamento");
        System.out.println("2. Acquista un biglietto");
        System.out.println("3. Esci");
        int scelta = scanner.nextInt();
        scanner.nextLine();
        switch (scelta) {
            case 1:
                System.out.println("Inserisci durata dell'abbonamento: ");
                System.out.println("1. SETTIMANALE");
                System.out.println("2. MENSILE");
                int durata = scanner.nextInt();
                scanner.nextLine();
                DurataValidita durataValidita = null;
                switch (durata) {
                    case 1:
                        durataValidita = DurataValidita.SETTIMANALE;
                        break;
                    case 2:
                        durataValidita = DurataValidita.MENSILE;
                        break;
                    default:
                        System.out.println("Scelta non valida");
                }
                System.out.println("inserisci l'anno corrente");
                int anno = scanner.nextInt();
                scanner.nextLine();
                System.out.println("inserisci il mese corrente");
                int mese = scanner.nextInt();
                scanner.nextLine();
                System.out.println("inserisci il giorno corrente");
                int giorno = scanner.nextInt();
                scanner.nextLine();
                System.out.println("inserisci il codice id dell'emissione: ");
                long codiceEmissione = scanner.nextLong();
                scanner.nextLine();
                System.out.println("inserisci l'id della tessera: ");
                long idTessera = scanner.nextLong();
                scanner.nextLine();
                TitoloDiViaggio abbonamento = new Abbonamento(durataValidita, LocalDate.of(anno, mese, giorno), getById(codiceEmissione), (Tessera) tdvDAO.getById(idTessera));
                try {
                    tdvDAO.save(abbonamento);
                } catch (Exception e) {
                    tdvDAO.update(abbonamento);
                }
                break;
            case 2:
                System.out.println("Inserisci l'anno corrente");
                int anno2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Inserisci il mese corrente");
                int mese2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Inserisci il giorno corrente");
                int giorno2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Inserisci il codice id dell'emissione: ");
                long codiceEmissione2 = scanner.nextLong();
                scanner.nextLine();
                TitoloDiViaggio biglietto = new Biglietto(LocalDate.of(anno2, mese2, giorno2), getById(codiceEmissione2));
                try {
                    tdvDAO.save(biglietto);
                } catch (Exception e) {
                    tdvDAO.update(biglietto);
                }
                break;
            case 3:
                System.out.println("Hai selezionato: Esci");
                break;
            default:
                System.out.println("Scelta non valida");
                break;

        }
    }
        public boolean titoloValido (){
            return false;
        }

        public void controllaValidita (){

        }




    }







