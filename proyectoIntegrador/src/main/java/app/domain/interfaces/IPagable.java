package app.domain.interfaces;

/**
 * Principio de Segregación de Interfaces (ISP):
 * Contrato específico para métodos o procesadores de pago en el cine.
 */
public interface IPagable {
    boolean procesarPago(double monto);
    String getDetallePago();
}
