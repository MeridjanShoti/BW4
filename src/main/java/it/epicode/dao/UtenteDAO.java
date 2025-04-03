package it.epicode.dao;

import it.epicode.entities.persone.Utente;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente persona) {
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
    }

    public Utente getById(int id) {
        return em.find(Utente.class, id);
    }

    public void update(Utente persona) {
        em.getTransaction().begin();
        em.merge(persona);
        em.getTransaction().commit();
    }

    public void delete(Utente persona) {
        em.getTransaction().begin();
        em.remove(persona);
        em.getTransaction().commit();
    }

    public void updateNoTx(Utente persona) {
        em.merge(persona);
    }

    public void deleteNoTx(Utente persona) {
        em.remove(persona);
    }

    public void saveNoTx(Utente persona) {
        em.persist(persona);
    }

    public void creaUtente(String nome, String cognome) {
        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);

        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
        System.out.println("Utente creato: " + nome + " " + cognome);
        System.out.println("id: " + utente.getId());
    }

    public void creaTessera(Utente utente, LocalDate dataEmissione) {
        Tessera tessera = new Tessera();
        tessera.setUtente(utente);
        tessera.setDataEmissione(dataEmissione);

        em.getTransaction().begin();
        em.persist(tessera);
        em.getTransaction().commit();

        utente.setTessera(tessera);
        System.out.println("Tessera creata per " + utente.getNome() + " " + utente.getCognome());
        System.out.println("id: " + tessera.getId());
        update(utente);
    }


}
