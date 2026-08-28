package app.domain;

public class Reserva {
    private int id;
    private Cliente cliente;
    private Funcion funcion;
    private int cantidadBoletos;
    private String estado;

    public Reserva() {
        this.estado = "Pendiente";
    }

    public Reserva(int id, Cliente cliente, Funcion funcion, int cantidadBoletos) {
        this.id = id;
        this.cliente = cliente;
        this.funcion = funcion;
        this.cantidadBoletos = cantidadBoletos;
        this.estado = "Pendiente";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public void setCantidadBoletos(int cantidadBoletos) {
        this.cantidadBoletos = cantidadBoletos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double calcularSubtotal() {
        if (funcion != null) {
            return funcion.getPrecioBoleto() * cantidadBoletos;
        }
        return 0;
    }

    public double calcularDescuento() {
        double subtotal = calcularSubtotal();
        if (cantidadBoletos > 4 || (cliente != null && cliente.getEdad() < 12)) {
            return subtotal * 0.10;
        }
        return 0;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }

    public boolean esValidaPorEdad() {
        if (cliente != null && funcion != null && funcion.getPelicula() != null) {
            return funcion.getPelicula().esAptaPara(cliente.getEdad());
        }
        return false;
    }

    public void confirmar() {
        if (esValidaPorEdad()) {
            this.estado = "Confirmada";
        } else {
            System.out.println("No se puede confirmar la reserva: el cliente no cumple con la edad mínima.");
        }
    }

    @Override
    public String toString() {
        return "Reserva #" + id + " [" + estado + "] - " + cliente.getNombre() + " (" + cantidadBoletos + " boletos) - Total: $" + calcularTotal();
    }
}
