package app.domain;

/**
 * Pilar POO: Herencia y Polimorfismo.
 * Representa a un empleado o administrador del cine.
 */
public class Empleado extends Persona {
    private String cargo;
    private double salario;

    public Empleado() {
        super();
    }

    public Empleado(int id, String nombre, String correo, String telefono, String cargo, double salario) {
        super(id, nombre, correo, telefono);
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String getTipoPersona() {
        return "Empleado (" + cargo + ")";
    }
}
