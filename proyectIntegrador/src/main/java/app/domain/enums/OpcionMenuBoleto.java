package app.domain.enums;

public enum OpcionMenuBoleto {
    EMITIR(1, "Emitir nuevo boleto"),
    CONSULTAR_POR_ID(2, "Consultar boleto por ID"),
    LISTAR_TODOS(3, "Listar todos los boletos"),
    ACTUALIZAR(4, "Actualizar boleto"),
    ELIMINAR(5, "Eliminar boleto"),
    SALIR(6, "Volver al menú principal");

    private final int codigo;
    private final String descripcion;

    OpcionMenuBoleto(int codigo, String descripcion) {
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
