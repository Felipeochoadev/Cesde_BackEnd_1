package app.service.dto;

public class ClienteResponseDTO {
    private int id;
    private String nombre;
    private String correo;
    private String telefono;
    private int edad;
    private boolean esMayorDeEdad;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(int id, String nombre, String correo, String telefono, int edad, boolean esMayorDeEdad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;
        this.esMayorDeEdad = esMayorDeEdad;
    }

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
        this.nombre = nombre;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isEsMayorDeEdad() {
        return esMayorDeEdad;
    }

    public void setEsMayorDeEdad(boolean esMayorDeEdad) {
        this.esMayorDeEdad = esMayorDeEdad;
    }
}
