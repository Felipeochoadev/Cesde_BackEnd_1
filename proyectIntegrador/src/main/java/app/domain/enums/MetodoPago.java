package app.domain.enums;

public enum MetodoPago {
    EFECTIVO("Efectivo"),
    TARJETA("Tarjeta de Crédito / Débito"),
    NEQUI("Nequi"),
    DAVIPLATA("Daviplata");

    private final String nombre;

    // Constructor del Enum
    MetodoPago(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
