package app.domain;

/**
 * Pilar POO: Herencia y Polimorfismo.
 * Principio Liskov Substitution (LSP):
 * Representa a un cliente del cine, con atributo de edad para validaciones de clasificación y descuentos.
 */
public class Cliente extends Persona {
    private int edad;

    public Cliente() {
        super();
    }

    public Cliente(int id, String nombre, String correo, String telefono, int edad) {
        super(id, nombre, correo, telefono);
        setEdad(edad);
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0 || edad > 120) {
            throw new IllegalArgumentException("La edad debe ser un número válido entre 0 y 120");
        }
        this.edad = edad;
    }

    public boolean esMayorDeEdad() {
        return this.edad >= 18;
    }

    @Override
    public String getTipoPersona() {
        return "Cliente";
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", correo='" + getCorreo() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", edad=" + edad +
                '}';
    }
}
