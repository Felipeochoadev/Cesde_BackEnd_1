package app.domain.enums;

public enum OpcionMenuReserva {
    REGISTRAR(1, "Registrar nueva reserva"),
    CONSULTAR_POR_ID(2, "Consultar reserva por ID"),
    LISTAR_TODOS(3, "Listar todas las reservas"),
    ACTUALIZAR(4, "Actualizar reserva"),
    ELIMINAR(5, "Eliminar reserva"),
    SALIR(6, "Volver al menú principal");

    private final int codigo;
    private final String descripcion;

    OpcionMenuReserva(int codigo, String descripcion) {
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
