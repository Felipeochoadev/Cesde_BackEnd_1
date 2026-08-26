package app.domain.enums;

public enum ClasificacionEdad {
    TODOS("TP", "Todo Público", 0),
    MAYORES_7("+7", "Mayores de 7 años", 7),
    MAYORES_12("+12", "Mayores de 12 años", 12),
    MAYORES_15("+15", "Mayores de 15 años", 15),
    MAYORES_18("+18", "Mayores de 18 años (Exclusivo Adultos)", 18);

    private final String codigo;
    private final String descripcion;
    private final int edadMinima;

    ClasificacionEdad(String codigo, String descripcion, int edadMinima) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.edadMinima = edadMinima;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public boolean esAptaPara(int edad) {
        return edad >= this.edadMinima;
    }
}
