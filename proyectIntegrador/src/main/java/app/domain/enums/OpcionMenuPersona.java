package app.domain.enums;

public enum OpcionMenuPersona {
    REGISTRAR(1, "Registrar nueva persona"),
    CONSULTAR_POR_ID(2, "Consultar persona por ID"),
    LISTAR_TODOS(3, "Listar todas las personas"),
    ACTUALIZAR(4, "Actualizar persona"),
    ELIMINAR(5, "Eliminar persona"),
    SALIR(6, "Volver al menú principal");

    private final int codigo;
    private final String descripcion;

    OpcionMenuPersona(int codigo, String descripcion) {
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
