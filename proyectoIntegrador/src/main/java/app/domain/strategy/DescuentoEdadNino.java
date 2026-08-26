package app.domain.strategy;

import app.domain.Cliente;

/**
 * Estrategia de descuento para niños (< 12 años).
 */
public class DescuentoEdadNino implements IDescuentoStrategy {
    private final int edadLimite;
    private final double porcentaje;

    public DescuentoEdadNino() {
        this(12, 0.10);
    }

    public DescuentoEdadNino(int edadLimite, double porcentaje) {
        this.edadLimite = edadLimite;
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularDescuento(double subtotal, int cantidadBoletos, Cliente cliente) {
        if (cliente != null && cliente.getEdad() < edadLimite) {
            return subtotal * porcentaje;
        }
        return 0.0;
    }

    @Override
    public String getDescripcion() {
        return "Descuento Infantil (" + (int)(porcentaje * 100) + "% para menores de " + edadLimite + " años)";
    }
}
