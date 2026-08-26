package app.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Pelicula (Correspondiente a tabla Peliculas en BD CineMaxCesde).
 * Encapsula la información de la película y su clasificación de edad.
 */
public class Pelicula {
    private int id;
    private String nombre;
    private int duracionMinutos;
    private double precioBase;
    private LocalDate ultimaFuncion;
    private Categoria categoria;
    private Clasificacion clasificacion;
    private List<Formato> formatosDisponibles;

    public Pelicula() {
        this.formatosDisponibles = new ArrayList<>();
    }

    public Pelicula(int id, String nombre, int duracionMinutos, double precioBase, LocalDate ultimaFuncion, Categoria categoria, Clasificacion clasificacion) {
        this.id = id;
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.precioBase = precioBase;
        this.ultimaFuncion = ultimaFuncion;
        this.categoria = categoria;
        this.clasificacion = clasificacion;
        this.formatosDisponibles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la película es requerido");
        }
        this.nombre = nombre.trim();
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precioBase = precioBase;
    }

    public LocalDate getUltimaFuncion() {
        return ultimaFuncion;
    }

    public void setUltimaFuncion(LocalDate ultimaFuncion) {
        this.ultimaFuncion = ultimaFuncion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<Formato> getFormatosDisponibles() {
        return formatosDisponibles;
    }

    public void setFormatosDisponibles(List<Formato> formatosDisponibles) {
        this.formatosDisponibles = formatosDisponibles;
    }

    public void agregarFormato(Formato formato) {
        if (formato != null && !this.formatosDisponibles.contains(formato)) {
            this.formatosDisponibles.add(formato);
        }
    }

    /**
     * Regla de negocio: Determina si un cliente cumple la edad requerida para ver la película.
     */
    public boolean esAptaPara(int edad) {
        if (this.clasificacion == null) {
            return true;
        }
        return this.clasificacion.esAptaPara(edad);
    }

    @Override
    public String toString() {
        return nombre + " (" + duracionMinutos + " min) - $" + precioBase + " COP";
    }
}
