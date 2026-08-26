package app.domain.strategy;

import app.domain.Cliente;

/**
 * Estrategia de descuento por volumen de compra (> 4 boletos).
 */
public class DescuentoCantidadBoletos implements IDescuentoStrategy {
    private final int minBoletos;
    private final double porcentaje;

    public DescuentoCantidadBoletos() {
        this(4, 0.10);
    }

    public DescuentoCantidadBoletos(int minBoletos, double porcentaje) {
        this.minBoletos = minBoletos;
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularDescuento(double subtotal, int cantidadBoletos, Cliente cliente) {
        if (cantidadBoletos > minBoletos) {
            return subtotal * porcentaje;
        }
        return 0.0;
    }

    @Override
    public String getDescripcion() {
        return "Descuento por Volumen (" + (int)(porcentaje * 100) + "% por compras mayores a " + minBoletos + " boletos)";
    }
}
