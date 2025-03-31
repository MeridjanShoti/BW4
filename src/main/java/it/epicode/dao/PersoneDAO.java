package it.epicode.dao;

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
}
