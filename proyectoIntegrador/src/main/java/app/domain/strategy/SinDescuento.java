package app.domain.strategy;

import app.domain.Cliente;

/**
 * Estrategia nula (Null Object Pattern / Default Strategy) que no aplica descuento.
 */
public class SinDescuento implements IDescuentoStrategy {

    @Override
    public double calcularDescuento(double subtotal, int cantidadBoletos, Cliente cliente) {
        return 0.0;
    }

    @Override
    public String getDescripcion() {
        return "Sin Descuento Aplicado (Tarifa Plena)";
    }
}
