package app.service.helpers;

import app.domain.enums.EstadoReserva;
import app.domain.enums.GeneroPelicula;
import app.domain.enums.MetodoPago;
import app.domain.enums.OpcionMenuCliente;
import app.domain.enums.TipoSala;
import app.service.validations.FormTypeValidator;

public class SetPropertyHelper {

    public static OpcionMenuCliente setOpcionMenuCliente() {
        System.out.println("\n=== MENÚ DE GESTIÓN DE CLIENTES ===");
        OpcionMenuCliente[] opciones = OpcionMenuCliente.values();
        
        // Muestra cada opción en su propio salto de línea con su código y descripción
        for (OpcionMenuCliente op : opciones) {
            System.out.println(op.getCodigo() + ". " + op.getDescripcion());
        }

        while (true) {
            int seleccion = FormTypeValidator.readInt("Seleccione una opción (1-" + opciones.length + "): ");
            for (OpcionMenuCliente op : opciones) {
                if (op.getCodigo() == seleccion) {
                    return op;
                }
            }
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + opciones.length);
        }
    }

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
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + generos.length);
        }
    }

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
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + tipos.length);
        }
    }

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
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + metodos.length);
        }
    }

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
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + estados.length);
        }
    }
}
