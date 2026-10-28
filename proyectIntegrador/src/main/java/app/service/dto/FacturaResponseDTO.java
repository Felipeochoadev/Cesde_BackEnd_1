package app.service.dto;

public class FacturaResponseDTO {
    private int id;
    private String numero;
    private int reservaId;
    private String cliente;
    private String pelicula;
    private int cantidadBoletos;
    private String metodoPago;
    private boolean pagada;
    private double total;

    public FacturaResponseDTO() {}

    public FacturaResponseDTO(int id, String numero, int reservaId, String cliente, String pelicula, 
                              int cantidadBoletos, String metodoPago, boolean pagada, double total) {
        this.id = id;
        this.numero = numero;
        this.reservaId = reservaId;
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.cantidadBoletos = cantidadBoletos;
        this.metodoPago = metodoPago;
        this.pagada = pagada;
        this.total = total;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public int getReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getPelicula() { return pelicula; }
    public void setPelicula(String pelicula) { this.pelicula = pelicula; }
    public int getCantidadBoletos() { return cantidadBoletos; }
    public void setCantidadBoletos(int cantidadBoletos) { this.cantidadBoletos = cantidadBoletos; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public boolean isPagada() { return pagada; }
    public void setPagada(boolean pagada) { this.pagada = pagada; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
