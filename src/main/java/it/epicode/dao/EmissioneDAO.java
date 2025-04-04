package it.epicode.dao;

import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.titoli_di_viaggio.Abbonamento;
import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import it.epicode.entities.titoli_di_viaggio.TitoloDiViaggio;
import it.epicode.enums.DurataValidita;
import it.epicode.exceptions.ErroreDiInserimentoException;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmissioneDAO {
    private EntityManager em;
    private UtenteDAO utenteDAO;
    private TitoliDiViaggioDAO tdvDAO;

    public EmissioneDAO(EntityManager em) {
        this.em = em;
        this.utenteDAO = new UtenteDAO(em);
        this.tdvDAO = new TitoliDiViaggioDAO(em);
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
        System.out.println("0. Esci");
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
                System.out.println("Inserisci durata dell'abbonamento: ");
                System.out.println("1. SETTIMANALE");
                System.out.println("2. MENSILE");
                int durata = 0;
                try {
                    durata = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("dovevi inserire un numero!");
                } finally {
                    scanner.nextLine();
                }
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
                if(durataValidita == null) {
                    return;
                }
                System.out.println("inserisci l'anno corrente");
                int anno = 0;
                try {
                    anno = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("dovevi inserire un numero!");
                } finally {
                    scanner.nextLine();
                }
                if (anno == 0) {
                    return;
                }
                System.out.println("inserisci il mese corrente");
                int mese = 0;
                try {
                    mese = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("dovevi inserire un numero!");
                } finally {
                    scanner.nextLine();
                }
                if (mese == 0) {
                    return;
                }
                System.out.println("inserisci il giorno corrente");
                int giorno = 0;
                try {
                    giorno = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("dovevi inserire un numero!");
                } finally {
                    scanner.nextLine();
                }
                if (giorno == 0) {
                    return;
                }
                System.out.println("inserisci il codice id dell'emissione: ");
                long codiceEmissione = 0L;
                try {
                    codiceEmissione = scanner.nextLong();
                } catch (Exception e) {
                    System.out.println("dovevi inserire un numero!");
                } finally {
                    scanner.nextLine();
                }
                if (codiceEmissione == 0L) {
                    return;
                }
                System.out.println("inserisci l'id della tessera: ");
                long idTessera = 0L;
                try {
                    idTessera = scanner.nextLong();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("dovevi inserire un numero!");
                }
                if (idTessera == 0L) {
                    return;
                }
                TitoloDiViaggio abbonamento = new Abbonamento(durataValidita, LocalDate.of(anno, mese, giorno), getById(codiceEmissione), (Tessera) tdvDAO.getById(idTessera));
                try {
                    if (anno == 0 || mese == 0 || giorno == 0 || codiceEmissione == 0 || idTessera == 0L) {
                        throw new ErroreDiInserimentoException("Errore nell'inserimento");
                    }
                    tdvDAO.save(abbonamento);
                    tdvDAO.update(tdvDAO.getById(idTessera));
                    System.out.println("Abbonamento con id " + abbonamento.getId() + " acquistato con successo!");
                } catch (ErroreDiInserimentoException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Abbonamento non emesso");
                }
                break;
            case 2:
                System.out.println("Inserisci l'anno corrente");
                int anno2 = 0;
                try {
                    anno2 = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("dovevi inserire un numero!");
                } finally {
                    scanner.nextLine();
                }
                if (anno2 == 0) {
                    return;
                }
                System.out.println("Inserisci il mese corrente");
                int mese2 = 0;
                try {
                    mese2 = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("dovevi inserire un numero!");
                } finally {
                    scanner.nextLine();
                }
                if (mese2 == 0) {
                    return;
                }
                System.out.println("Inserisci il giorno corrente");
                int giorno2 = 0;
                try {
                    giorno2 = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("dovevi inserire un numero!");
                } finally {
                    scanner.nextLine();
                }
                if (giorno2 == 0) {
                    return;
                }
                System.out.println("Inserisci il codice id dell'emissione: ");
                long codiceEmissione2 = 0L;
                try {
                    codiceEmissione2 = scanner.nextLong();
                } catch (Exception e) {
                    System.out.println("dovevi inserire un numero!");
                } finally {
                    scanner.nextLine();
                }
                if (codiceEmissione2 == 0L) {
                    return;
                }
                TitoloDiViaggio biglietto = new Biglietto(LocalDate.of(anno2, mese2, giorno2), getById(codiceEmissione2));
                try {
                    if (anno2 == 0 || mese2 == 0 || giorno2 == 0 || codiceEmissione2 == 0L) {
                        throw new ErroreDiInserimentoException("Errore nell'inserimento");
                    }
                    tdvDAO.save(biglietto);
                    System.out.println("Biglietto con id " + biglietto.getId() + " acquistato con successo!");
                } catch (ErroreDiInserimentoException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Biglietto non emesso");
                }
                break;
            case 0:
                System.out.println("Hai selezionato: Esci");
                break;
            default:
                System.out.println("Scelta non valida");
                break;

        }
    }


}







