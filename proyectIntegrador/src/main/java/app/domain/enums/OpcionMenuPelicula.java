package app.domain.enums;

public enum OpcionMenuPelicula {
    REGISTRAR(1, "Registrar película en cartelera"),
    CONSULTAR_POR_ID(2, "Consultar película por ID"),
    LISTAR_TODOS(3, "Listar todas las películas"),
    ACTUALIZAR(4, "Actualizar película"),
    ELIMINAR(5, "Eliminar película"),
    SALIR(6, "Volver al menú principal");

    private final int codigo;
    private final String descripcion;

    OpcionMenuPelicula(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return codigo + ". " + descripcion;
    }
}
