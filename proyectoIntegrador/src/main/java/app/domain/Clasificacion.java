package app.domain;

import app.domain.enums.ClasificacionEdad;

/**
 * Entidad Clasificacion (Restricción de edad en BD CineMaxCesde).
 */
public class Clasificacion {
    private int id;
    private String codigo;
    private String descripcion;
    private int edadMinima;

    public Clasificacion() {
    }

    public Clasificacion(int id, String codigo, String descripcion, int edadMinima) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.edadMinima = edadMinima;
    }

    public Clasificacion(int id, ClasificacionEdad enumClasificacion) {
        this.id = id;
        this.codigo = enumClasificacion.getCodigo();
        this.descripcion = enumClasificacion.getDescripcion();
        this.edadMinima = enumClasificacion.getEdadMinima();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public boolean esAptaPara(int edad) {
        return edad >= this.edadMinima;
    }

    @Override
    public String toString() {
        return codigo + " (" + descripcion + ")";
    }
}
