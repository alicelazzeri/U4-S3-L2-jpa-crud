package it.epicode.classes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventoDAO {
    private EntityManagerFactory emf;

    public EventoDAO(EntityManagerFactory emf){
        this.emf = emf;
    }

    private static final Logger logger = LoggerFactory.getLogger(EventoDAO.class);

    public void save (Evento evento) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Eccezione riscontrata durante il salvataggio dell'evento", e);
        } finally {
            em.close();
        }
    }

    public Evento getByID (long id) {
        EntityManager em = emf.createEntityManager();

        try {
            Evento item = em.find(Evento.class, id);
            if( item == null ) {
                throw new NoResultException("Nessun evento trovato con ID {}" + id);
            }
            return item;
        } catch (NoResultException e) {
            logger.error("Eccezione riscontrata durante il reperimento dell'entità", e);
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Evento evento) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(evento);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Eccezione riscontrata durante l'eliminazione dell'evento",e);
        } finally {
            em.close();
            emf.close();
        }
    }



}
