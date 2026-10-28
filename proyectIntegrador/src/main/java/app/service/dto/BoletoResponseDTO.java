package app.service.dto;

public class BoletoResponseDTO {
    private int id;
    private String codigo;
    private int funcionId;
    private String pelicula;
    private String sala;
    private String asiento;
    private double precio;

    public BoletoResponseDTO() {}

    public BoletoResponseDTO(int id, String codigo, int funcionId, String pelicula, String sala, String asiento, double precio) {
        this.id = id;
        this.codigo = codigo;
        this.funcionId = funcionId;
        this.pelicula = pelicula;
        this.sala = sala;
        this.asiento = asiento;
        this.precio = precio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public int getFuncionId() { return funcionId; }
    public void setFuncionId(int funcionId) { this.funcionId = funcionId; }
    public String getPelicula() { return pelicula; }
    public void setPelicula(String pelicula) { this.pelicula = pelicula; }
    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }
    public String getAsiento() { return asiento; }
    public void setAsiento(String asiento) { this.asiento = asiento; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
