package app.domain;

public class Boleto {
    private int id;
    private String codigo;
    private Funcion funcion;
    private String asiento;
    private double precio;

    public Boleto() {
    }

    public Boleto(int id, String codigo, Funcion funcion, String asiento) {
        this.id = id;
        this.codigo = codigo;
        this.funcion = funcion;
        this.asiento = asiento;
        this.precio = (funcion != null) ? funcion.getPrecioBoleto() : 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
        this.precio = (funcion != null) ? funcion.getPrecioBoleto() : 0;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Boleto #" + codigo + " [Asiento: " + asiento + "] - $" + precio;
    }
}
