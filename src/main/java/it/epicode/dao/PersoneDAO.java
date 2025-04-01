package it.epicode.dao;

import it.epicode.entities.persone.Utente;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import jakarta.persistence.EntityManager;

public class PersoneDAO {
    private EntityManager em;
    public PersoneDAO(EntityManager em) {
        this.em = em;
    }
    public void save(Object persona) {
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
    }
    public Object getById(int id) {
        return em.find(Object.class, id);
    }
    public void update(Object persona) {
        em.getTransaction().begin();
        em.merge(persona);
        em.getTransaction().commit();
    }
    public void delete(Object persona) {
        em.getTransaction().begin();
        em.remove(persona);
        em.getTransaction().commit();
    }
    public void updateNoTx(Object persona) {
        em.merge(persona);
    }
    public void deleteNoTx(Object persona) {
        em.remove(persona);
    }
    public void saveNoTx(Object persona) {
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
    }

    public void creaTessera(Utente utente) {
        Tessera tessera = new Tessera();
        tessera.setUtente(utente);

        em.getTransaction().begin();
        em.persist(tessera);
        em.getTransaction().commit();

        utente.setTessera(tessera);
        System.out.println("Tessera creata per " + utente.getNome() + " " + utente.getCognome());
    }




}
