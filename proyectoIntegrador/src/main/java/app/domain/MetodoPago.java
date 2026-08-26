package app.domain;

import app.domain.interfaces.IPagable;

/**
 * Principio Liskov Substitution (LSP) e Interface Segregation (ISP):
 * Clase abstracta que define la estructura de cualquier método de pago en CineMax.
 */
public abstract class MetodoPago implements IPagable {
    private int id;
    private String nombre;

    public MetodoPago() {
    }

    public MetodoPago(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public abstract boolean procesarPago(double monto);

    @Override
    public abstract String getDetallePago();

    @Override
    public String toString() {
        return nombre;
    }
}
