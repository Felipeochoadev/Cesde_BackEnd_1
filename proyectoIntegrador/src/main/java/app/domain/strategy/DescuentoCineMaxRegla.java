package app.domain.strategy;

import app.domain.Cliente;

/**
 * Estrategia de descuento oficial de CineMax CESDE (Regla extraída de Cesde-Logica):
 * Si la cantidad de boletos es mayor a 4 O la edad del cliente es menor a 12 años,
 * se aplica un descuento del 10% sobre el subtotal.
 */
public class DescuentoCineMaxRegla implements IDescuentoStrategy {
    private final double porcentaje;

    public DescuentoCineMaxRegla() {
        this.porcentaje = 0.10; // 10%
    }

    public DescuentoCineMaxRegla(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularDescuento(double subtotal, int cantidadBoletos, Cliente cliente) {
        int edad = (cliente != null) ? cliente.getEdad() : 18;
        if (cantidadBoletos > 4 || edad < 12) {
            return subtotal * porcentaje;
        }
        return 0.0;
    }

    @Override
    public String getDescripcion() {
        return "Descuento CineMax CESDE (" + (int)(porcentaje * 100) + "% por más de 4 boletos o cliente < 12 años)";
    }
}
