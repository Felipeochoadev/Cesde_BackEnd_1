package app.domain.enums;

public enum TipoSala {
    DOS_D("2D Tradicional", 0.0),
    TRES_D("3D", 3000.0),
    VIP("Sala VIP", 5000.0),
    IMAX("Sala IMAX", 8000.0);

    private final String descripcion;
    private final double recargo;

    // Constructor del Enum con descripción y recargo asociado
    TipoSala(String descripcion, double recargo) {
        this.descripcion = descripcion;
        this.recargo = recargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getRecargo() {
        return recargo;
    }

    @Override
    public String toString() {
        return descripcion + " (Recargo: $" + recargo + ")";
    }
}
