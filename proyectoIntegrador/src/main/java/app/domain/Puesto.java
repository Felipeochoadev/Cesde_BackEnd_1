package app.domain;

import app.domain.enums.TipoPuesto;

/**
 * Entidad Puesto / Asiento (Tabla Puestos en BD CineMaxCesde).
 */
public class Puesto {
    private int id;
    private String fila;
    private int numero;
    private TipoPuesto tipo;
    private boolean ocupado;

    public Puesto() {
        this.tipo = TipoPuesto.GENERAL;
        this.ocupado = false;
    }

    public Puesto(int id, String fila, int numero, TipoPuesto tipo) {
        this.id = id;
        this.fila = fila;
        this.numero = numero;
        this.tipo = tipo;
        this.ocupado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoPuesto getTipo() {
        return tipo;
    }

    public void setTipo(TipoPuesto tipo) {
        this.tipo = tipo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getCodigoAsiento() {
        return fila + numero;
    }

    public double getRecargo() {
        return (tipo != null) ? tipo.getRecargo() : 0.0;
    }

    @Override
    public String toString() {
        return getCodigoAsiento() + " (" + tipo.getNombre() + ")";
    }
}
