package app.domain.interfaces;

/**
 * Principio de Segregación de Interfaces (ISP):
 * Define el contrato exclusivo para entidades u objetos cuyo valor económico puede ser calculado.
 */
public interface ICalculable {
    double calcularTotal();
}
