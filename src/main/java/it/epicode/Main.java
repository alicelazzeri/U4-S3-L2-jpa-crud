package it.epicode;

import it.epicode.classes.Evento;
import it.epicode.classes.TipoEvento;
import it.epicode.dao.EventoDao;
import it.epicode.dao.JpaEventoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {

        int numeroEventi = 40;

        try (JpaEventoDao eventoDao = new JpaEventoDao()) {
            List<Evento> eventi = IntStream.range(1, 41)
                    .mapToObj(e -> new Evento(e, "Evento " + e, LocalDate.of(2024, 5, 1).plusDays(e - 1),
                            "Descrizione evento" + e, TipoEvento.PUBBLICO, 80))
                    .toList();

            eventi.forEach(evento -> eventoDao.save(evento));

            eventoDao.getById(12);

            Evento eventoToDelete = eventoDao.getById(12).orElse(null);
            if (eventoToDelete != null) {
                eventoDao.delete(eventoToDelete);
            }

        } catch (Exception e) {
            logger.error("Eccezione riscontrata nell'esecuzione del main()", e);
        }
    }



}