package app.domain;

public class Cliente extends Persona {
    private int edad;

    public Cliente() {
        super();
    }

    public Cliente(int id, String nombre, String correo, String telefono, int edad) {
        super(id, nombre, correo, telefono);
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean esMayorDeEdad() {
        return this.edad >= 18;
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombre() + " (" + edad + " años)";
    }
}
