package app.domain.enums;

public enum ModuloSistema {
    CLIENTES(1, "Gestión de Clientes"),
    PERSONAS(2, "Gestión de Personas"),
    PELICULAS(3, "Gestión de Películas (Cartelera)"),
    SALAS(4, "Gestión de Salas"),
    FUNCIONES(5, "Gestión de Funciones"),
    BOLETOS(6, "Gestión de Boletos"),
    RESERVAS(7, "Gestión de Reservas"),
    FACTURAS(8, "Gestión de Facturas"),
    CAMBIAR_AMBIENTE(9, "Configurar Ambiente de Persistencia (Global)"),
    SALIR(10, "Salir de CineMax CESDE");

    private final int codigo;
    private final String descripcion;

    ModuloSistema(int codigo, String descripcion) {
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
