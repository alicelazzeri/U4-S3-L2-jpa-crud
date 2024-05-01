package it.epicode.dao;

import it.epicode.classes.Evento;

import java.util.Optional;

public interface EventoDao extends AutoCloseable {
    void save(Evento evento);
    Optional <Evento> getById(long id);
    void delete(Evento evento);
}
