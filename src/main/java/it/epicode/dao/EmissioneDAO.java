package it.epicode.dao;

import it.epicode.entities.emissioni.Emissione;
import it.epicode.entities.persone.Utente;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class EmissioneDAO {
    private EntityManager em;

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

    public static void creaTesseraPerUtente(EntityManager em, Utente utente) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creazione tessera per l'utente: " + utente.getNome() + " " + utente.getCognome());
        System.out.print("Inserisci il numero della tessera: ");
        Long numeroTessera = scanner.nextLong();


        Tessera tessera = new Tessera();
        tessera.setId(numeroTessera);
        tessera.setUtente(utente);


        em.getTransaction().begin();
        em.persist(tessera);
        em.getTransaction().commit();

        utente.setTessera(tessera);
    }
}
