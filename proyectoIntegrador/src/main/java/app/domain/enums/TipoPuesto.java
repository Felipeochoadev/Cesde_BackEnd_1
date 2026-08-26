package app.domain.enums;

public enum TipoPuesto {
    GENERAL("General", 0.0),
    VIP("VIP Premium", 3500.0);

    private final String nombre;
    private final double recargo;

    TipoPuesto(String nombre, double recargo) {
        this.nombre = nombre;
        this.recargo = recargo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getRecargo() {
        return recargo;
    }
}
