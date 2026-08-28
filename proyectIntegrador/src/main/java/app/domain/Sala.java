package app.domain;

public class Sala {
    private int id;
    private String nombre;
    private int capacidad;
    private String tipo;
    private double recargo;

    public Sala() {
    }

    public Sala(int id, String nombre, int capacidad, String tipo, double recargo) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.recargo = recargo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    @Override
    public String toString() {
        return nombre + " [" + tipo + " - Capacidad: " + capacidad + "]";
    }
}
