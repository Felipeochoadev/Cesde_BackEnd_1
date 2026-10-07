package app.domain.enums;

public enum OpcionMenuCliente {
    REGISTRAR(1, "Registrar nuevo cliente"),
    CONSULTAR_POR_ID(2, "Consultar cliente por ID"),
    LISTAR_TODOS(3, "Listar todos los clientes"),
    ACTUALIZAR(4, "Actualizar cliente"),
    ELIMINAR(5, "Eliminar cliente"),
    SALIR(6, "Salir del sistema");

    private final int codigo;
    private final String descripcion;

    OpcionMenuCliente(int codigo, String descripcion) {
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
