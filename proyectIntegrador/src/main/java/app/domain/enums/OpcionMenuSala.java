package app.domain.enums;

public enum OpcionMenuSala {
    REGISTRAR(1, "Registrar nueva sala"),
    CONSULTAR_POR_ID(2, "Consultar sala por ID"),
    LISTAR_TODOS(3, "Listar todas las salas"),
    ACTUALIZAR(4, "Actualizar sala"),
    ELIMINAR(5, "Eliminar sala"),
    SALIR(6, "Volver al menú principal");

    private final int codigo;
    private final String descripcion;

    OpcionMenuSala(int codigo, String descripcion) {
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
