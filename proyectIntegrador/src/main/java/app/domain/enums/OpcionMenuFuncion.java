package app.domain.enums;

public enum OpcionMenuFuncion {
    PROGRAMAR(1, "Programar nueva función"),
    CONSULTAR_POR_ID(2, "Consultar función por ID"),
    LISTAR_TODOS(3, "Listar todas las funciones"),
    ACTUALIZAR(4, "Actualizar función"),
    ELIMINAR(5, "Eliminar función"),
    SALIR(6, "Volver al menú principal");

    private final int codigo;
    private final String descripcion;

    OpcionMenuFuncion(int codigo, String descripcion) {
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
