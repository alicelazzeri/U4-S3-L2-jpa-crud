package it.epicode;


import it.epicode.classes.Evento;
import it.epicode.classes.EventoDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String PERSISTENCE_UNIT = "gestioneEventi";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);


        try {
            EventoDAO eventoDAO = new EventoDAO(emf);
            Evento evento = new Evento(1, "Concerto dell'Orchestra di Ateneo",
                    LocalDate.of(2024, 04, 27),
                    "Il 27 aprile alle 21:15, al Teatro Verdi " +
                            "l'Orchestra di ateneo si esibirà in concerto, " +
                            "in occasione della Giornata della solidarietà, " +
                            "organizzata dall'Associazione Nicola Cialdelli.",
                    Evento.tipoEvento.PUBBLICO, 300);

            eventoDAO.save(evento);

            long idRicercato = 1;
            Evento eventoTrovato = eventoDAO.getByID(idRicercato);
            if (eventoTrovato != null) {
                System.out.println("Risultato della ricerca: " + eventoTrovato);
            } else {
                System.out.println("Nessun evento con id " + idRicercato + " trovato.");
            }

            eventoDAO.delete(eventoTrovato);


        } catch (Exception e) {
            logger.error("Eccezione riscontrata durante l'esecuzione delle operazioni", e);
        } finally {

            emf.close();

        }

    }
}