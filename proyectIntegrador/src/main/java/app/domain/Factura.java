package app.domain;

public class Factura {
    private int id;
    private String numero;
    private Reserva reserva;
    private String metodoPago;
    private boolean pagada;

    public Factura() {
    }

    public Factura(int id, String numero, Reserva reserva, String metodoPago) {
        this.id = id;
        this.numero = numero;
        this.reserva = reserva;
        this.metodoPago = metodoPago;
        this.pagada = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public void pagar() {
        if (reserva != null && reserva.esValidaPorEdad()) {
            this.pagada = true;
            reserva.confirmar();
        } else {
            System.out.println("No se puede realizar el pago de una reserva inválida.");
        }
    }

    public void imprimirFactura() {
        System.out.println("----------------------------------------");
        System.out.println("            FACTURA DE VENTA            ");
        System.out.println("----------------------------------------");
        System.out.println("Factura N°:    " + numero);
        if (reserva != null) {
            if (reserva.getCliente() != null) {
                System.out.println("Cliente:       " + reserva.getCliente().getNombre());
                System.out.println("Correo:        " + reserva.getCliente().getCorreo());
            }
            if (reserva.getFuncion() != null) {
                System.out.println("Película:      " + reserva.getFuncion().getPelicula().getTitulo());
                System.out.println("Sala:          " + reserva.getFuncion().getSala().getNombre());
                System.out.println("Fecha/Hora:    " + reserva.getFuncion().getFecha() + " " + reserva.getFuncion().getHora());
            }
            System.out.println("Cantidad:      " + reserva.getCantidadBoletos() + " boletos");
            System.out.println("Subtotal:      $" + reserva.calcularSubtotal());
            System.out.println("Descuento:     -$" + reserva.calcularDescuento());
            System.out.println("Total a pagar: $" + reserva.calcularTotal());
        }
        System.out.println("Método Pago:   " + metodoPago);
        System.out.println("Estado:        " + (pagada ? "PAGADO" : "PENDIENTE"));
        System.out.println("----------------------------------------\n");
    }

    @Override
    public String toString() {
        return "Factura #" + numero + " - Estado: " + (pagada ? "PAGADO" : "PENDIENTE");
    }
}
