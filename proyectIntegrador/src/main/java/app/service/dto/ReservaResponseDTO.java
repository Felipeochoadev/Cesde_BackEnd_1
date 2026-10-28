package app.service.dto;

public class ReservaResponseDTO {
    private int id;
    private String cliente;
    private String pelicula;
    private String sala;
    private String fecha;
    private String hora;
    private int cantidadBoletos;
    private double subtotal;
    private double descuento;
    private double total;
    private String estado;

    public ReservaResponseDTO() {}

    public ReservaResponseDTO(int id, String cliente, String pelicula, String sala, String fecha, String hora, 
                              int cantidadBoletos, double subtotal, double descuento, double total, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.cantidadBoletos = cantidadBoletos;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getPelicula() { return pelicula; }
    public void setPelicula(String pelicula) { this.pelicula = pelicula; }
    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public int getCantidadBoletos() { return cantidadBoletos; }
    public void setCantidadBoletos(int cantidadBoletos) { this.cantidadBoletos = cantidadBoletos; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
