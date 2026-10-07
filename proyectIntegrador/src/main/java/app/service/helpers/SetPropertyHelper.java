package app.service.helpers;

import app.domain.enums.EstadoReserva;
import app.domain.enums.GeneroPelicula;
import app.domain.enums.MetodoPago;
import app.domain.enums.TipoSala;
import app.service.validations.FormTypeValidator;

public class SetPropertyHelper {

    /**
     * Muestra el menú de géneros de películas disponibles y retorna el Enum seleccionado.
     */
    public static GeneroPelicula setGeneroPelicula() {
        System.out.println("\nSeleccione el Género de la Película:");
        GeneroPelicula[] generos = GeneroPelicula.values();
        for (int i = 0; i < generos.length; i++) {
            System.out.println((i + 1) + ". " + generos[i].getDescripcion());
        }

        while (true) {
            int opcion = FormTypeValidator.readInt("Elija una opción (1-" + generos.length + "): ");
            if (opcion >= 1 && opcion <= generos.length) {
                return generos[opcion - 1];
            }
            System.out.println("❌ Opción inválida. Ingrese un número entre 1 y " + generos.length);
        }
    }

    /**
     * Muestra el menú de tipos de sala con su recargo y retorna el Enum seleccionado.
     */
    public static TipoSala setTipoSala() {
        System.out.println("\nSeleccione el Tipo de Sala:");
        TipoSala[] tipos = TipoSala.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + ". " + tipos[i].getDescripcion() + " [Recargo: $" + tipos[i].getRecargo() + "]");
        }

        while (true) {
            int opcion = FormTypeValidator.readInt("Elija una opción (1-" + tipos.length + "): ");
            if (opcion >= 1 && opcion <= tipos.length) {
                return tipos[opcion - 1];
            }
            System.out.println("❌ Opción inválida. Ingrese un número entre 1 y " + tipos.length);
        }
    }

    /**
     * Muestra los métodos de pago disponibles y retorna el Enum seleccionado.
     */
    public static MetodoPago setMetodoPago() {
        System.out.println("\nSeleccione el Método de Pago:");
        MetodoPago[] metodos = MetodoPago.values();
        for (int i = 0; i < metodos.length; i++) {
            System.out.println((i + 1) + ". " + metodos[i].getNombre());
        }

        while (true) {
            int opcion = FormTypeValidator.readInt("Elija una opción (1-" + metodos.length + "): ");
            if (opcion >= 1 && opcion <= metodos.length) {
                return metodos[opcion - 1];
            }
            System.out.println("❌ Opción inválida. Ingrese un número entre 1 y " + metodos.length);
        }
    }

    /**
     * Muestra los estados de reserva posibles y retorna el Enum seleccionado.
     */
    public static EstadoReserva setEstadoReserva() {
        System.out.println("\nSeleccione el Estado de la Reserva:");
        EstadoReserva[] estados = EstadoReserva.values();
        for (int i = 0; i < estados.length; i++) {
            System.out.println((i + 1) + ". " + estados[i].getDescripcion());
        }

        while (true) {
            int opcion = FormTypeValidator.readInt("Elija una opción (1-" + estados.length + "): ");
            if (opcion >= 1 && opcion <= estados.length) {
                return estados[opcion - 1];
            }
            System.out.println("❌ Opción inválida. Ingrese un número entre 1 y " + estados.length);
        }
    }
}
