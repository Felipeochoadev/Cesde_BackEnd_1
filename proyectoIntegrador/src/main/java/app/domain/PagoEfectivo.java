package app.domain;

/**
 * Pago en Efectivo en taquilla CineMax.
 */
public class PagoEfectivo extends MetodoPago {
    private double montoRecibido;
    private double cambio;

    public PagoEfectivo(int id, double montoRecibido) {
        super(id, "Efectivo");
        this.montoRecibido = montoRecibido;
    }

    public double getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(double montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    public double getCambio() {
        return cambio;
    }

    @Override
    public boolean procesarPago(double monto) {
        if (montoRecibido >= monto) {
            this.cambio = montoRecibido - monto;
            return true;
        }
        return false;
    }

    @Override
    public String getDetallePago() {
        return "Pago en Efectivo - Recibido: $" + montoRecibido + " COP, Cambio: $" + cambio + " COP";
    }
}
