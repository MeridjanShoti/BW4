package it.epicode.dao;

import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.exceptions.ErroreDiInserimentoException;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MezzoDAO {
    private EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Mezzo mezzo) {
        em.getTransaction().begin();
        em.persist(mezzo);
        em.getTransaction().commit();
    }

    public Mezzo getById(long id) {
        return em.find(Mezzo.class, id);
    }

    public void update(Mezzo mezzo) {
        em.getTransaction().begin();
        em.merge(mezzo);
        em.getTransaction().commit();
    }

    public void delete(Mezzo mezzo) {
        em.getTransaction().begin();
        em.remove(mezzo);
        em.getTransaction().commit();
    }

    public void updateNoTx(Mezzo mezzo) {
        em.merge(mezzo);
    }

    public void deleteNoTx(Mezzo mezzo) {
        em.remove(mezzo);
    }

    public void saveNoTx(Mezzo mezzo) {
        em.persist(mezzo);
    }

    public void timbraBigliettoDaId(Biglietto biglietto, Mezzo mezzo) {
        if (biglietto.isValidita()) {
            TitoliDiViaggioDAO bigliettoDAO = new TitoliDiViaggioDAO(em);
            Scanner sc = new Scanner(System.in);
            biglietto.timbraBiglietto();
            mezzo.setNumeroBigliettiVidimati(mezzo.getNumeroBigliettiVidimati() + 1);
            System.out.println("che anno è?");
            int anno = 0;
            try {
                anno = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("dovevi inserire un numero!");
            } finally {
                sc.nextLine();
            }
            System.out.println("che mese è?");
            int mese = 0;
            try {
                mese = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("dovevi inserire un numero!");
            } finally {
                sc.nextLine();
            }
            System.out.println("che giorno è?");
            int giorno = 0;
            try {
                giorno = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("dovevi inserire un numero!");
            } finally {
                sc.nextLine();
            }
            try {
                biglietto.setDataTimbro(LocalDate.of(anno, mese, giorno));
                biglietto.setMezzo(mezzo);
                bigliettoDAO.update(biglietto);
            } catch (ErroreDiInserimentoException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("biglietto non valido scendi subito");
        }
    }
}