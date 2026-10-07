package app.domain.enums;

public enum GeneroPelicula {
    ACCION("Acción"),
    COMEDIA("Comedia"),
    DRAMA("Drama"),
    TERROR("Terror"),
    ANIMACION("Animación"),
    CIENCIA_FICCION("Ciencia Ficción");

    private final String descripcion;

    // Constructor del Enum
    GeneroPelicula(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
