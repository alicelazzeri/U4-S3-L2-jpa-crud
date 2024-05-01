package it.epicode.dao;

import it.epicode.classes.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class JpaEventoDao implements EventoDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaEventoDao.class);
    private static final String PERSISTENCE_UNIT = "gestioneEventi";
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    private final EntityManager em = emf.createEntityManager();


    @Override
    public void save(Evento evento) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(evento);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error("Eccezione riscontrata durante il salvataggio", e);
        }
    }

    @Override
    public Optional<Evento> getById(long id) {
        Evento item = em.find(Evento.class, id);
        if (item == null) {
            return Optional.empty();
        }
        return Optional.of(item);
    }

    @Override
    public void delete(Evento evento) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(evento);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error("Eccezione riscontrata durante l'eliminazione dell'oggetto", e);
        }

    }

    @Override
    public void close() throws Exception {
        em.close();
        emf.close();

    }
}
