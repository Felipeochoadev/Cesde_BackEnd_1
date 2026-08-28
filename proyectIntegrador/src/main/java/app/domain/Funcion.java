package app.domain;

public class Funcion {
    private int id;
    private Pelicula pelicula;
    private Sala sala;
    private String fecha;
    private String hora;

    public Funcion() {
    }

    public Funcion(int id, Pelicula pelicula, Sala sala, String fecha, String hora) {
        this.id = id;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getPrecioBoleto() {
        double precioPeli = (pelicula != null) ? pelicula.getPrecioBase() : 0;
        double recargoSala = (sala != null) ? sala.getRecargo() : 0;
        return precioPeli + recargoSala;
    }

    @Override
    public String toString() {
        return pelicula.getTitulo() + " en " + sala.getNombre() + " (" + fecha + " " + hora + ")";
    }
}
