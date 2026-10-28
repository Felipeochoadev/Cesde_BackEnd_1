package app.service.dto;

public class FuncionResponseDTO {
    private int id;
    private String pelicula;
    private String sala;
    private String fecha;
    private String hora;
    private double precioBoleto;

    public FuncionResponseDTO() {}

    public FuncionResponseDTO(int id, String pelicula, String sala, String fecha, String hora, double precioBoleto) {
        this.id = id;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.precioBoleto = precioBoleto;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPelicula() { return pelicula; }
    public void setPelicula(String pelicula) { this.pelicula = pelicula; }
    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public double getPrecioBoleto() { return precioBoleto; }
    public void setPrecioBoleto(double precioBoleto) { this.precioBoleto = precioBoleto; }
}
