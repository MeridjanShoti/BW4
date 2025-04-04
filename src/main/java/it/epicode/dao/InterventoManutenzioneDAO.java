package it.epicode.dao;

import it.epicode.entities.mezzi.InterventoManutenzione;
import it.epicode.entities.mezzi.Mezzo;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;


public class InterventoManutenzioneDAO {
    private EntityManager em;

    public InterventoManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(InterventoManutenzione interventoManutenzione) {
        try {
            em.getTransaction().begin();
            em.persist(interventoManutenzione);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void delete(InterventoManutenzione interventoManutenzione) {
        try {
            em.getTransaction().begin();
            em.remove(interventoManutenzione);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

    }

    public void update(InterventoManutenzione interventoManutenzione) {
        try {
            em.getTransaction().begin();
            em.merge(interventoManutenzione);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public InterventoManutenzione getById(int id) {
        return em.find(InterventoManutenzione.class, id);
    }

    public void saveNoTx(InterventoManutenzione interventoManutenzione) {
        em.persist(interventoManutenzione);
    }

    public void deleteNoTx(InterventoManutenzione interventoManutenzione) {
        em.remove(interventoManutenzione);
    }

    public void updateNoTx(InterventoManutenzione interventoManutenzione) {
        em.merge(interventoManutenzione);
    }

    public void iniziaManutenzione(InterventoManutenzione interventoManutenzione, LocalDate inizioManutenzione, Mezzo mezzo) {
        interventoManutenzione.setInizioManutenzione(inizioManutenzione);
        interventoManutenzione.setMezzoInManutenzione(mezzo);
        update(interventoManutenzione);
    }

    public void finisciManutenzione(InterventoManutenzione interventoManutenzione, LocalDate localDate) {
        interventoManutenzione.setFineManutenzione(localDate);
        update(interventoManutenzione);
    }
    public List<InterventoManutenzione> trovaManutenzioniByMezzo(Mezzo mezzo) {
        return em.createNamedQuery("InterventoManutenzione.checkManutenzioneByMezzo", InterventoManutenzione.class).setParameter("mezzoInManutenzione", mezzo).getResultList();
    }
    public List<InterventoManutenzione> trovaManutenzioniByData(LocalDate data1, LocalDate data2) {
        return em.createNamedQuery("InterventoManutenzione.checkManutenzioneByData", InterventoManutenzione.class).setParameter("inizioManutenzione", data1).setParameter("fineManutenzione", data2).getResultList();
    }
    public List<InterventoManutenzione> trovaManutenzioni() {
        return em.createNamedQuery("InterventoManutenzione.checkManutenzioneByTutti", InterventoManutenzione.class).getResultList();
    }

}
