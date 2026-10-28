package app.domain.enums;

public enum OpcionMenuFactura {
    GENERAR(1, "Generar nueva factura"),
    CONSULTAR_POR_ID(2, "Consultar factura por ID"),
    LISTAR_TODOS(3, "Listar todas las facturas"),
    ACTUALIZAR(4, "Actualizar factura"),
    ELIMINAR(5, "Eliminar factura"),
    SALIR(6, "Volver al menú principal");

    private final int codigo;
    private final String descripcion;

    OpcionMenuFactura(int codigo, String descripcion) {
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
