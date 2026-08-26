package app.domain.strategy;

import app.domain.Cliente;

/**
 * Principio Abierto/Cerrado (OCP) e Inversión de Dependencias (DIP):
 * Define la estrategia para calcular descuentos en reservas de CineMax.
 * Nuevas promociones pueden ser agregadas sin modificar las clases Reserva o Factura.
 */
public interface IDescuentoStrategy {
    double calcularDescuento(double subtotal, int cantidadBoletos, Cliente cliente);
    String getDescripcion();
}
