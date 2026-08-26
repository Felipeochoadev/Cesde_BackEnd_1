package app.domain;

import app.domain.interfaces.ICalculable;

/**
 * Entidad Boleto / Ticket (Tabla Tikets en BD CineMaxCesde).
 * Implementa ICalculable (ISP).
 */
public class Boleto implements ICalculable {
    private int id;
    private String codigoBoleto;
    private Funcion funcion;
    private Puesto puesto;
    private double precio;

    public Boleto() {
    }

    public Boleto(int id, String codigoBoleto, Funcion funcion, Puesto puesto) {
        this.id = id;
        this.codigoBoleto = codigoBoleto;
        this.funcion = funcion;
        this.puesto = puesto;
        this.precio = calcularTotal();
        if (puesto != null) {
            puesto.setOcupado(true);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoBoleto() {
        return codigoBoleto;
    }

    public void setCodigoBoleto(String codigoBoleto) {
        this.codigoBoleto = codigoBoleto;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
        this.precio = calcularTotal();
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
        this.precio = calcularTotal();
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public double calcularTotal() {
        double base = (funcion != null) ? funcion.getPrecioBoletoBase() : 0.0;
        double recargoPuesto = (puesto != null) ? puesto.getRecargo() : 0.0;
        return base + recargoPuesto;
    }

    @Override
    public String toString() {
        return "Boleto [" + codigoBoleto + "] - Puesto: " + (puesto != null ? puesto.getCodigoAsiento() : "N/A") +
                " - Precio: $" + precio + " COP";
    }
}
