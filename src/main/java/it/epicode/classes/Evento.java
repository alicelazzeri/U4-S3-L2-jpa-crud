package it.epicode.classes;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.StringJoiner;

@Entity
@Table(name ="evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column (name="titolo", length = 50, nullable = true)
    private String titolo;
    @Column (name = "data_evento", nullable = true)
    private LocalDate dataEvento;
    @Column (name="descrizione", length = 100, nullable = true)
    private String descrizione;
    @Column (name="tipo_evento", nullable = true)
    private tipoEvento tipoEvento;
    @Column (name="numero_max_partecipanti", nullable = true)
    private int numeroMassimoPartecipanti;

    public enum tipoEvento {
        PUBBLICO,
        PRIVATO
    }

    public Evento() {}

    public Evento(long id, String titolo, LocalDate dataEvento, String descrizione, tipoEvento tipoEvento, int numeroMassimoPartecipanti){
        this.id = id;
        this.titolo = titolo;
        this.dataEvento= dataEvento;
        this.descrizione = descrizione;
        this.tipoEvento = tipoEvento;
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Evento.tipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(Evento.tipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getNumeroMassimoPartecipanti() {
        return numeroMassimoPartecipanti;
    }

    public void setNumeroMassimoPartecipanti(int numeroMassimoPartecipanti) {
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Evento.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("titolo='" + titolo + "'")
                .add("dataEvento=" + dataEvento)
                .add("descrizione='" + descrizione + "'")
                .add("tipoEvento=" + tipoEvento)
                .add("numeroMassimoPartecipanti=" + numeroMassimoPartecipanti)
                .toString();
    }
}
