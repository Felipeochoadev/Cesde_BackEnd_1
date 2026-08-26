package app.domain.enums;

public enum EstadoReserva {
    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada");

    private final String descripcion;

    EstadoReserva(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
