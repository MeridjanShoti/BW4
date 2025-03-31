package it.epicode.dao;

import it.epicode.entities.titoli_di_viaggio.TitoloDiViaggio;
import jakarta.persistence.EntityManager;

public class TitoliDiViaggioDAO {
    private EntityManager em;

    public TitoliDiViaggioDAO(EntityManager em) {
        this.em = em;
    }
    public void save(TitoloDiViaggio titoloDiViaggio) {
        try {
            em.getTransaction().begin();
            em.persist(titoloDiViaggio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
    public TitoloDiViaggio getById(Long id) {
        return em.find(TitoloDiViaggio.class, id);
    }
    public void delete(TitoloDiViaggio titoloDiViaggio) {
        try {
            em.getTransaction().begin();
            em.remove(titoloDiViaggio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
    public void update(TitoloDiViaggio titoloDiViaggio) {
        try {
            em.getTransaction().begin();
            em.merge(titoloDiViaggio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

    }
    public void updateNoTx(TitoloDiViaggio titoloDiViaggio) {
        em.merge(titoloDiViaggio);
    }
    public void deleteNoTx(TitoloDiViaggio titoloDiViaggio) {
        em.remove(titoloDiViaggio);
    }
    public void saveNoTx(TitoloDiViaggio titoloDiViaggio) {
        em.persist(titoloDiViaggio);
    }
}
