package app.domain;

public class Pelicula {
    private int id;
    private String titulo;
    private String genero;
    private int duracionMinutos;
    private double precioBase;
    private int edadMinima;

    public Pelicula() {
    }

    public Pelicula(int id, String titulo, String genero, int duracionMinutos, double precioBase, int edadMinima) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.duracionMinutos = duracionMinutos;
        this.precioBase = precioBase;
        this.edadMinima = edadMinima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public boolean esAptaPara(int edadCliente) {
        return edadCliente >= this.edadMinima;
    }

    @Override
    public String toString() {
        return titulo + " (" + genero + ", " + duracionMinutos + " min) - $" + precioBase;
    }
}
