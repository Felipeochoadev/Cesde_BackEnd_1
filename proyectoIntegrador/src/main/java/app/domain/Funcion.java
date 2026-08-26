package app.domain;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entidad Funcion (Tabla Funciones en BD CineMaxCesde).
 * Representa una proyección programada de una película en una sala y horario específico.
 */
public class Funcion {
    private int id;
    private Pelicula pelicula;
    private Sala sala;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Funcion() {
    }

    public Funcion(int id, Pelicula pelicula, Sala sala, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * Calcula el precio unitario base del boleto para esta función considerando la película y el formato de la sala.
     */
    public double getPrecioBoletoBase() {
        double precio = (pelicula != null) ? pelicula.getPrecioBase() : 0.0;
        if (sala != null && sala.getFormato() != null) {
            precio += sala.getFormato().getPrecioAdicional();
        }
        return precio;
    }

    @Override
    public String toString() {
        return "Función #" + id + ": " + (pelicula != null ? pelicula.getNombre() : "N/A") +
                " en " + (sala != null ? sala.getNombre() : "N/A") +
                " [" + fecha + " " + horaInicio + " - " + horaFin + "]";
    }
}
