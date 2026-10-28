package app.service.helpers;

import app.domain.enums.*;
import app.service.validations.FormTypeValidator;

public class SetPropertyHelper {

    public static OpcionMenuCliente setOpcionMenuCliente() {
        System.out.println("\n=== MENÚ DE GESTIÓN DE CLIENTES ===");
        OpcionMenuCliente[] opciones = OpcionMenuCliente.values();
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

    public static OpcionMenuPersona setOpcionMenuPersona() {
        System.out.println("\n=== MENÚ DE GESTIÓN DE PERSONAS ===");
        OpcionMenuPersona[] opciones = OpcionMenuPersona.values();
        for (OpcionMenuPersona op : opciones) {
            System.out.println(op.getCodigo() + ". " + op.getDescripcion());
        }
        while (true) {
            int seleccion = FormTypeValidator.readInt("Seleccione una opción (1-" + opciones.length + "): ");
            for (OpcionMenuPersona op : opciones) {
                if (op.getCodigo() == seleccion) {
                    return op;
                }
            }
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + opciones.length);
        }
    }

    public static OpcionMenuPelicula setOpcionMenuPelicula() {
        System.out.println("\n=== MENÚ DE GESTIÓN DE PELÍCULAS ===");
        OpcionMenuPelicula[] opciones = OpcionMenuPelicula.values();
        for (OpcionMenuPelicula op : opciones) {
            System.out.println(op.getCodigo() + ". " + op.getDescripcion());
        }
        while (true) {
            int seleccion = FormTypeValidator.readInt("Seleccione una opción (1-" + opciones.length + "): ");
            for (OpcionMenuPelicula op : opciones) {
                if (op.getCodigo() == seleccion) {
                    return op;
                }
            }
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + opciones.length);
        }
    }

    public static OpcionMenuSala setOpcionMenuSala() {
        System.out.println("\n=== MENÚ DE GESTIÓN DE SALAS ===");
        OpcionMenuSala[] opciones = OpcionMenuSala.values();
        for (OpcionMenuSala op : opciones) {
            System.out.println(op.getCodigo() + ". " + op.getDescripcion());
        }
        while (true) {
            int seleccion = FormTypeValidator.readInt("Seleccione una opción (1-" + opciones.length + "): ");
            for (OpcionMenuSala op : opciones) {
                if (op.getCodigo() == seleccion) {
                    return op;
                }
            }
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + opciones.length);
        }
    }

    public static OpcionMenuFuncion setOpcionMenuFuncion() {
        System.out.println("\n=== MENÚ DE GESTIÓN DE FUNCIONES ===");
        OpcionMenuFuncion[] opciones = OpcionMenuFuncion.values();
        for (OpcionMenuFuncion op : opciones) {
            System.out.println(op.getCodigo() + ". " + op.getDescripcion());
        }
        while (true) {
            int seleccion = FormTypeValidator.readInt("Seleccione una opción (1-" + opciones.length + "): ");
            for (OpcionMenuFuncion op : opciones) {
                if (op.getCodigo() == seleccion) {
                    return op;
                }
            }
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + opciones.length);
        }
    }

    public static OpcionMenuBoleto setOpcionMenuBoleto() {
        System.out.println("\n=== MENÚ DE GESTIÓN DE BOLETOS ===");
        OpcionMenuBoleto[] opciones = OpcionMenuBoleto.values();
        for (OpcionMenuBoleto op : opciones) {
            System.out.println(op.getCodigo() + ". " + op.getDescripcion());
        }
        while (true) {
            int seleccion = FormTypeValidator.readInt("Seleccione una opción (1-" + opciones.length + "): ");
            for (OpcionMenuBoleto op : opciones) {
                if (op.getCodigo() == seleccion) {
                    return op;
                }
            }
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + opciones.length);
        }
    }

    public static OpcionMenuReserva setOpcionMenuReserva() {
        System.out.println("\n=== MENÚ DE GESTIÓN DE RESERVAS ===");
        OpcionMenuReserva[] opciones = OpcionMenuReserva.values();
        for (OpcionMenuReserva op : opciones) {
            System.out.println(op.getCodigo() + ". " + op.getDescripcion());
        }
        while (true) {
            int seleccion = FormTypeValidator.readInt("Seleccione una opción (1-" + opciones.length + "): ");
            for (OpcionMenuReserva op : opciones) {
                if (op.getCodigo() == seleccion) {
                    return op;
                }
            }
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + opciones.length);
        }
    }

    public static OpcionMenuFactura setOpcionMenuFactura() {
        System.out.println("\n=== MENÚ DE GESTIÓN DE FACTURAS ===");
        OpcionMenuFactura[] opciones = OpcionMenuFactura.values();
        for (OpcionMenuFactura op : opciones) {
            System.out.println(op.getCodigo() + ". " + op.getDescripcion());
        }
        while (true) {
            int seleccion = FormTypeValidator.readInt("Seleccione una opción (1-" + opciones.length + "): ");
            for (OpcionMenuFactura op : opciones) {
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

    public static ModuloSistema setModuloSistema() {
        System.out.println("\n==================================================");
        System.out.println("          MENÚ PRINCIPAL - CINEMAX CESDE");
        System.out.println("==================================================");
        ModuloSistema[] modulos = ModuloSistema.values();
        for (ModuloSistema mod : modulos) {
            System.out.println(mod.getCodigo() + ". " + mod.getDescripcion());
        }
        while (true) {
            int seleccion = FormTypeValidator.readInt("Seleccione un módulo (1-" + modulos.length + "): ");
            for (ModuloSistema mod : modulos) {
                if (mod.getCodigo() == seleccion) {
                    return mod;
                }
            }
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + modulos.length);
        }
    }

    public static Ambiente setAmbiente() {
        System.out.println("\n=== CONFIGURAR AMBIENTE GLOBAL DE PERSISTENCIA ===");
        Ambiente[] ambientes = Ambiente.values();
        for (int i = 0; i < ambientes.length; i++) {
            System.out.println((i + 1) + ". " + ambientes[i].name());
        }
        while (true) {
            int seleccion = FormTypeValidator.readInt("Seleccione el nuevo ambiente (1-" + ambientes.length + "): ");
            if (seleccion >= 1 && seleccion <= ambientes.length) {
                return ambientes[seleccion - 1];
            }
            System.out.println("Opción inválida. Ingrese un número entre 1 y " + ambientes.length);
        }
    }
}
