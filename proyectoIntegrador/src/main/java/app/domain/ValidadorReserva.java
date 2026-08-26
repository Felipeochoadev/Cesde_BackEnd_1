package app.domain;

import app.domain.interfaces.IValidador;

/**
 * Principio de Responsabilidad Única (SRP) e Inversión de Dependencias (DIP):
 * Validador de reglas de negocio para reservas en CineMax CESDE.
 * 
 * Reglas implementadas (extraídas de Cesde-Logica):
 * 1. Campos obligatorios: Nombre, Boletos, Edad.
 * 2. Control de boletos: Boletos > 0.
 * 3. Restricción de edad: Si la película es para mayores (ej. +18) y el cliente no cumple la edad mínima.
 */
public class ValidadorReserva implements IValidador<Reserva> {

    @Override
    public void validar(Reserva reserva) throws IllegalArgumentException, IllegalStateException {
        if (reserva == null) {
            throw new IllegalArgumentException("❌ La reserva no puede ser nula.");
        }

        Cliente cliente = reserva.getCliente();
        if (cliente == null || cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Por favor, ingresa los datos del cliente correctamente.");
        }

        if (reserva.getCantidadBoletos() <= 0) {
            throw new IllegalArgumentException("❌ La cantidad de boletos debe ser mayor a 0.");
        }

        Funcion funcion = reserva.getFuncion();
        if (funcion == null || funcion.getPelicula() == null) {
            throw new IllegalStateException("❌ La reserva debe tener una función y película asignadas.");
        }

        Pelicula pelicula = funcion.getPelicula();
        if (!pelicula.esAptaPara(cliente.getEdad())) {
            int edadMinima = (pelicula.getClasificacion() != null) ? pelicula.getClasificacion().getEdadMinima() : 18;
            throw new IllegalStateException(
                "🚫 Lo sentimos " + cliente.getNombre() + ", \"" + pelicula.getNombre() +
                "\" es para mayores de " + edadMinima + " años. Tu edad actual es " + cliente.getEdad() + " años."
            );
        }
    }
}
