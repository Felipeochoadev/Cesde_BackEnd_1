package app.view;

import app.domain.Cliente;
import app.domain.Funcion;
import app.domain.Reserva;
import app.service.inputports.ReservaServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class ReservaView {
    private final ReservaServiceInterface reservaService;

    public ReservaView(ReservaServiceInterface reservaService) {
        this.reservaService = reservaService;
    }

    public void createReserva(Cliente cliente, Funcion funcion) {
        System.out.println("\n--- REGISTRAR RESERVA ---");
        int cantidadBoletos = FormTypeValidator.readInt("Ingrese la Cantidad de boletos a comprar: ");

        if (funcion != null && funcion.getPelicula() != null && !funcion.getPelicula().esAptaPara(cliente.getEdad())) {
            System.out.println("Error: La película '" + funcion.getPelicula().getTitulo() + 
                               "' requiere edad mínima de " + funcion.getPelicula().getEdadMinima() + 
                               " años. El cliente tiene " + cliente.getEdad() + " años.");
            return;
        }

        Reserva reserva = reservaService.create(cliente, funcion, cantidadBoletos);
        System.out.println("Reserva registrada con éxito:");
        System.out.println(reserva);
        System.out.println("Subtotal:   $" + reserva.calcularSubtotal());
        System.out.println("Descuento: -$" + reserva.calcularDescuento());
        System.out.println("Total:      $" + reserva.calcularTotal());
    }

    public void selectReservaById() {
        System.out.println("\n--- CONSULTAR RESERVA POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la reserva a buscar: ");
        selectReservaById(id);
    }

    public void selectReservaById(int id) {
        Reserva reserva = reservaService.getById(id);
        if (reserva != null) {
            System.out.println("Reserva encontrada:");
            System.out.println(reserva);
        } else {
            System.out.println("No se encontró ninguna reserva con ID " + id);
        }
    }

    public void selectAllReservas() {
        System.out.println("\n--- LISTA DE RESERVAS ---");
        List<Reserva> reservas = reservaService.getAll();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            for (Reserva r : reservas) {
                System.out.println(r);
            }
        }
    }

    public void updateReserva(int id, Cliente nuevoCliente, Funcion nuevaFuncion) {
        System.out.println("\n--- ACTUALIZAR RESERVA ID (" + id + ") ---");
        Reserva existente = reservaService.getById(id);
        if (existente == null) {
            System.out.println("No existe reserva con ID " + id);
            return;
        }

        int nuevaCantidad = FormTypeValidator.readIntOptional("Ingrese la nueva Cantidad de boletos", existente.getCantidadBoletos());
        Cliente clienteFinal = (nuevoCliente != null) ? nuevoCliente : existente.getCliente();
        Funcion funcionFinal = (nuevaFuncion != null) ? nuevaFuncion : existente.getFuncion();

        Reserva actualizada = new Reserva(id, clienteFinal, funcionFinal, nuevaCantidad);
        reservaService.update(id, actualizada);
        System.out.println("Reserva actualizada con éxito:");
        System.out.println(actualizada);
    }

    public void deleteReserva() {
        System.out.println("\n--- ELIMINAR RESERVA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la reserva a eliminar: ");
        deleteReserva(id);
    }

    public void deleteReserva(int id) {
        boolean eliminada = reservaService.delete(id);
        if (eliminada) {
            System.out.println("Reserva con ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
