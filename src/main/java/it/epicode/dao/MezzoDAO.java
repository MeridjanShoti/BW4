package it.epicode.dao;

import it.epicode.entities.mezzi.Mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

}
