package it.epicode.dao;

import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.titoli_di_viaggio.Abbonamento;
import it.epicode.entities.titoli_di_viaggio.Biglietto;
import it.epicode.entities.titoli_di_viaggio.Tessera;
import it.epicode.entities.titoli_di_viaggio.TitoloDiViaggio;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class TitoliDiViaggioDAO {
    private  EntityManager em;

    public TitoliDiViaggioDAO(EntityManager em) {
        this.em = em;
    }
    public void save(TitoloDiViaggio titoloDiViaggio) {

            em.getTransaction().begin();
            em.persist(titoloDiViaggio);
            em.getTransaction().commit();

    }
    public TitoloDiViaggio getById(Long id) {
        return em.find(TitoloDiViaggio.class, id);
    }
    public void delete(TitoloDiViaggio titoloDiViaggio) {
            em.getTransaction().begin();
            em.remove(titoloDiViaggio);
            em.getTransaction().commit();
    }
    public void update(TitoloDiViaggio titoloDiViaggio) {

            em.getTransaction().begin();
            em.merge(titoloDiViaggio);
            em.getTransaction().commit();
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
    public Long findBigliettoByTempo(LocalDate data1, LocalDate data2) {
        return em.createNamedQuery("Biglietto.findBigliettoByTempo", Long.class).setParameter("data1", data1).setParameter("data2", data2).getSingleResult().longValue();
    }
    public Long countBigliettiByMezzo(Mezzo mezzo) {
        return em.createNamedQuery("Biglietto.countBigliettiByMezzo", Long.class).setParameter("mezzo", mezzo).getSingleResult().longValue();
    }
    public boolean checkAbbonamentoByTessera (Tessera tessera) {
        Abbonamento abbonamento = em.createNamedQuery("Abbonamento.checkValiditaByTessera", Abbonamento.class).setParameter("tessera", tessera).getSingleResult();
        if (abbonamento == null) return false;
        else return !abbonamento.getDataScadenza().isBefore(LocalDate.now());
    }
    // titoli_di_viaggio.CountByPuntoVenditaEData
    public Object[] countByPVEData(LocalDate data1, LocalDate data2) {
        return em.createNamedQuery("TitoloDiViaggio.CountByPuntoVenditaEData", Object[].class).setParameter("data1", data1).setParameter("data2", data2).getResultStream().toArray();
    }
}
