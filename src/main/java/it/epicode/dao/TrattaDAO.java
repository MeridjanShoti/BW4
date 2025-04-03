package it.epicode.dao;

import it.epicode.entities.tratte.Tratta;
import jakarta.persistence.EntityManager;

public class TrattaDAO {

    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta emissione) {
        try {
            em.getTransaction().begin();
            em.persist(emissione);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public Tratta getById(Long id) {
        return em.find(Tratta.class, id);
    }

    public void delete(Tratta emissione) {
        try {
            em.getTransaction().begin();
            em.remove(emissione);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void update(Tratta tratta) {
        try {
            em.getTransaction().begin();
            em.merge(tratta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void saveNoTx(Tratta tratta) {
        em.persist(tratta);
    }

    public void deleteNoTx(Tratta tratta) {
        em.remove(tratta);
    }

    public void updateNoTx(Tratta tratta) {
        em.merge(tratta);
    }
}

