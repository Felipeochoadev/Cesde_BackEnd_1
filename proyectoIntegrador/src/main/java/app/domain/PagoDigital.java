package app.domain;

/**
 * Pago Digital (Billeteras electrónicas: Nequi, Daviplata, PSE).
 */
public class PagoDigital extends MetodoPago {
    private String plataforma; // Nequi, Daviplata, PSE
    private String numeroCelular;
    private String codigoTransaccion;

    public PagoDigital(int id, String plataforma, String numeroCelular, String codigoTransaccion) {
        super(id, "Billetera Digital (" + plataforma + ")");
        this.plataforma = plataforma;
        this.numeroCelular = numeroCelular;
        this.codigoTransaccion = codigoTransaccion;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    @Override
    public boolean procesarPago(double monto) {
        return codigoTransaccion != null && !codigoTransaccion.isEmpty() && monto > 0;
    }

    @Override
    public String getDetallePago() {
        return "Billetera Digital: " + plataforma + " Cel: " + numeroCelular + " [Ref: " + codigoTransaccion + "]";
    }
}
