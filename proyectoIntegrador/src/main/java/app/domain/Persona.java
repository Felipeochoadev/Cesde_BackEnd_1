package app.domain;

/**
 * Pilar POO: Abstracción y Encapsulamiento.
 * Principio Liskov Substitution (LSP):
 * Clase base abstracta para cualquier actor humano en el sistema.
 */
public abstract class Persona {
    private int id;
    private String nombre;
    private String correo;
    private String telefono;

    public Persona() {
    }

    public Persona(int id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Getters y Setters con Encapsulamiento
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método polimórfico que entrega la descripción de la persona según su tipo.
     */
    public abstract String getTipoPersona();

    @Override
    public String toString() {
        return getTipoPersona() + " [ID=" + id + ", Nombre='" + nombre + "', Correo='" + correo + "']";
    }
}
