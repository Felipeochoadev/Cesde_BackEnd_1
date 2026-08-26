package app.domain;

import app.domain.interfaces.ICalculable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Entidad Factura (Tabla Facturas en BD CineMaxCesde).
 * Implementa ICalculable (ISP) y utiliza abstracción MetodoPago (DIP, LSP).
 */
public class Factura implements ICalculable {
    private int id;
    private String numeroFactura;
    private Reserva reserva;
    private MetodoPago metodoPago;
    private LocalDateTime fechaEmision;
    private double subtotal;
    private double descuento;
    private double total;
    private boolean pagada;

    public Factura() {
        this.fechaEmision = LocalDateTime.now();
        this.pagada = false;
    }

    public Factura(int id, String numeroFactura, Reserva reserva, MetodoPago metodoPago) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.reserva = reserva;
        this.metodoPago = metodoPago;
        this.fechaEmision = LocalDateTime.now();
        if (reserva != null) {
            this.subtotal = reserva.getSubtotal();
            this.descuento = reserva.getMontoDescuento();
            this.total = reserva.calcularTotal();
        }
        this.pagada = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        if (reserva != null) {
            this.subtotal = reserva.getSubtotal();
            this.descuento = reserva.getMontoDescuento();
            this.total = reserva.calcularTotal();
        }
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotal() {
        return total;
    }

    public boolean isPagada() {
        return pagada;
    }

    @Override
    public double calcularTotal() {
        return total;
    }

    /**
     * Procesa el cobro de la factura utilizando el método de pago configurado.
     */
    public boolean procesarPago() {
        if (metodoPago == null) {
            throw new IllegalStateException("No se ha configurado un método de pago para la factura.");
        }
        this.pagada = metodoPago.procesarPago(this.total);
        if (this.pagada && reserva != null) {
            reserva.confirmar();
        }
        return this.pagada;
    }

    /**
     * Genera la representación textual detallada del comprobante de venta.
     */
    public String generarComprobanteTexto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("====================================================\n");
        sb.append("                 CINEMAX CESDE                      \n");
        sb.append("          COMPROBANTE ELECTRÓNICO DE VENTA          \n");
        sb.append("====================================================\n");
        sb.append("Factura N°:     ").append(numeroFactura).append("\n");
        sb.append("Fecha Emisión:  ").append(fechaEmision.format(formatter)).append("\n");
        if (reserva != null && reserva.getCliente() != null) {
            sb.append("Cliente:        ").append(reserva.getCliente().getNombre()).append("\n");
            sb.append("Correo:         ").append(reserva.getCliente().getCorreo()).append("\n");
            sb.append("Edad:           ").append(reserva.getCliente().getEdad()).append(" años\n");
        }
        if (reserva != null && reserva.getFuncion() != null) {
            Funcion f = reserva.getFuncion();
            sb.append("Película:       ").append(f.getPelicula() != null ? f.getPelicula().getNombre() : "N/A").append("\n");
            sb.append("Sala:           ").append(f.getSala() != null ? f.getSala().getNombre() : "N/A").append("\n");
            sb.append("Horario:        ").append(f.getFecha()).append(" ").append(f.getHoraInicio()).append("\n");
        }
        sb.append("----------------------------------------------------\n");
        sb.append("Boletos:        ").append(reserva != null ? reserva.getCantidadBoletos() : 0).append("\n");
        sb.append("Subtotal:       $").append(String.format("%,.2f", subtotal)).append(" COP\n");
        if (descuento > 0) {
            sb.append("Descuento:     -$").append(String.format("%,.2f", descuento)).append(" COP\n");
            if (reserva != null && reserva.getDescuentoStrategy() != null) {
                sb.append("  ↳ Regla:      ").append(reserva.getDescuentoStrategy().getDescripcion()).append("\n");
            }
        }
        sb.append("TOTAL A PAGAR:  $").append(String.format("%,.2f", total)).append(" COP\n");
        sb.append("----------------------------------------------------\n");
        sb.append("Método de Pago: ").append(metodoPago != null ? metodoPago.getDetallePago() : "N/A").append("\n");
        sb.append("Estado Pago:    ").append(pagada ? "PAGADO EXITOSAMENTE ✅" : "PENDIENTE DE PAGO ⏳").append("\n");
        sb.append("====================================================\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Factura #" + numeroFactura + " - Total: $" + total + " COP [" + (pagada ? "PAGADA" : "PENDIENTE") + "]";
    }
}
