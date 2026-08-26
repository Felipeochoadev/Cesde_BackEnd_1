package app.domain;

/**
 * Pago con Tarjeta de Crédito o Débito.
 */
public class PagoTarjeta extends MetodoPago {
    private String franquicia;
    private String ultimosCuatroDigitos;
    private int cuotas;

    public PagoTarjeta(int id, String franquicia, String numeroTarjeta, int cuotas) {
        super(id, "Tarjeta de " + franquicia);
        this.franquicia = franquicia;
        this.cuotas = cuotas;
        if (numeroTarjeta != null && numeroTarjeta.length() >= 4) {
            this.ultimosCuatroDigitos = numeroTarjeta.substring(numeroTarjeta.length() - 4);
        } else {
            this.ultimosCuatroDigitos = "****";
        }
    }

    public String getFranquicia() {
        return franquicia;
    }

    public String getUltimosCuatroDigitos() {
        return ultimosCuatroDigitos;
    }

    public int getCuotas() {
        return cuotas;
    }

    @Override
    public boolean procesarPago(double monto) {
        // Simulación de pasarela de pagos
        return monto > 0;
    }

    @Override
    public String getDetallePago() {
        return "Tarjeta " + franquicia + " terminada en " + ultimosCuatroDigitos + " (" + cuotas + " cuota(s))";
    }
}
