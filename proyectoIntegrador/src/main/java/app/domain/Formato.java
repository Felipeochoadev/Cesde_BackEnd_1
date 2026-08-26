package app.domain;

/**
 * Entidad Formato de Proyección (2D, 3D, IMAX, VIP).
 */
public class Formato {
    private int id;
    private String nombre;
    private double precioAdicional;

    public Formato() {
    }

    public Formato(int id, String nombre, double precioAdicional) {
        this.id = id;
        this.nombre = nombre;
        this.precioAdicional = precioAdicional;
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

    public double getPrecioAdicional() {
        return precioAdicional;
    }

    public void setPrecioAdicional(double precioAdicional) {
        this.precioAdicional = precioAdicional;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
