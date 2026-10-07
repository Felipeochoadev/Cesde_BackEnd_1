package app.view;

import app.domain.Funcion;
import app.domain.Pelicula;
import app.domain.Sala;
import app.service.inputports.FuncionServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class FuncionView {
    private final FuncionServiceInterface funcionService;

    public FuncionView(FuncionServiceInterface funcionService) {
        this.funcionService = funcionService;
    }

    public void createFuncion(Pelicula pelicula, Sala sala) {
        System.out.println("\n--- PROGRAMAR FUNCIÓN ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la función: ");
        String fecha = FormTypeValidator.readString("Ingrese la Fecha (AAAA-MM-DD): ");
        String hora = FormTypeValidator.readString("Ingrese la Hora (HH:MM): ");

        Funcion funcion = funcionService.create(id, pelicula, sala, fecha, hora);
        System.out.println("✅ Función programada con éxito: " + funcion);
    }

    public void selectFuncionById() {
        System.out.println("\n--- CONSULTAR FUNCIÓN POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la función a buscar: ");
        selectFuncionById(id);
    }

    public void selectFuncionById(int id) {
        Funcion funcion = funcionService.getById(id);
        if (funcion != null) {
            System.out.println("Encontrada: " + funcion + " (Precio boleto: $" + funcion.getPrecioBoleto() + ")");
        } else {
            System.out.println("❌ No se encontró ninguna función con ID " + id);
        }
    }

    public void selectAllFunciones() {
        System.out.println("\n--- CARTELERA DE FUNCIONES PROGRAMADAS ---");
        List<Funcion> funciones = funcionService.getAll();
        if (funciones.isEmpty()) {
            System.out.println("No hay funciones programadas actualmente.");
        } else {
            for (Funcion f : funciones) {
                System.out.println("• " + f + " -> Precio: $" + f.getPrecioBoleto());
            }
        }
    }

    public void updateFuncion(int id, Pelicula nuevaPelicula, Sala nuevaSala) {
        System.out.println("\n--- ACTUALIZAR FUNCIÓN ID (" + id + ") ---");
        String nuevaFecha = FormTypeValidator.readString("Ingrese la nueva Fecha (AAAA-MM-DD): ");
        String nuevaHora = FormTypeValidator.readString("Ingrese la nueva Hora (HH:MM): ");

        Funcion actualizada = new Funcion(id, nuevaPelicula, nuevaSala, nuevaFecha, nuevaHora);
        funcionService.update(id, actualizada);
        System.out.println("✅ Función actualizada con éxito.");
    }

    public void deleteFuncion() {
        System.out.println("\n--- ELIMINAR FUNCIÓN ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la función a eliminar: ");
        deleteFuncion(id);
    }

    public void deleteFuncion(int id) {
        boolean eliminada = funcionService.delete(id);
        if (eliminada) {
            System.out.println("✅ Función con ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
