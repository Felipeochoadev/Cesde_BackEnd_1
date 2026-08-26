package app.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Sala (Tabla Salas en BD CineMaxCesde).
 */
public class Sala {
    private int id;
    private String nombre;
    private int capacidad;
    private Formato formato;
    private List<Puesto> puestos;

    public Sala() {
        this.puestos = new ArrayList<>();
    }

    public Sala(int id, String nombre, int capacidad, Formato formato) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.formato = formato;
        this.puestos = new ArrayList<>();
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
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public List<Puesto> getPuestos() {
        return puestos;
    }

    public void setPuestos(List<Puesto> puestos) {
        this.puestos = puestos;
    }

    public void agregarPuesto(Puesto puesto) {
        if (puesto != null) {
            this.puestos.add(puesto);
        }
    }

    @Override
    public String toString() {
        return nombre + " [" + (formato != null ? formato.getNombre() : "Estándar") + ", Capacidad: " + capacidad + "]";
    }
}
