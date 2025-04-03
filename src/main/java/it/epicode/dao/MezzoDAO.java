package it.epicode.dao;

import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.titoli_di_viaggio.Biglietto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
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

    public Mezzo getById(int id) {
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
            int anno = sc.nextInt();
            sc.nextLine();
            System.out.println("che mese è?");
            int mese = sc.nextInt();
            sc.nextLine();
            System.out.println("che giorno è?");
            int giorno = sc.nextInt();
            sc.nextLine();
            biglietto.setDataTimbro(LocalDate.of(anno, mese, giorno));
            biglietto.setMezzo(mezzo);
            bigliettoDAO.update(biglietto);
        } else {
            System.out.println("biglietto non valido scendi subito");
        }
    }
}