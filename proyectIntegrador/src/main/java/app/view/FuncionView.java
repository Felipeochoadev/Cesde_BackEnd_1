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
        String fecha = FormTypeValidator.readString("Ingrese la Fecha (AAAA-MM-DD): ");
        String hora = FormTypeValidator.readString("Ingrese la Hora (HH:MM): ");

        Funcion funcion = funcionService.create(pelicula, sala, fecha, hora);
        System.out.println("Función programada con éxito:");
        System.out.println(funcion);
    }

    public void selectFuncionById() {
        System.out.println("\n--- CONSULTAR FUNCIÓN POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la función a buscar: ");
        selectFuncionById(id);
    }

    public void selectFuncionById(int id) {
        Funcion funcion = funcionService.getById(id);
        if (funcion != null) {
            System.out.println("Función encontrada:");
            System.out.println(funcion);
        } else {
            System.out.println("No se encontró ninguna función con ID " + id);
        }
    }

    public void selectAllFunciones() {
        System.out.println("\n--- CARTELERA DE FUNCIONES PROGRAMADAS ---");
        List<Funcion> funciones = funcionService.getAll();
        if (funciones.isEmpty()) {
            System.out.println("No hay funciones programadas actualmente.");
        } else {
            for (Funcion f : funciones) {
                System.out.println(f);
            }
        }
    }

    public void updateFuncion(int id, Pelicula nuevaPelicula, Sala nuevaSala) {
        System.out.println("\n--- ACTUALIZAR FUNCIÓN ID (" + id + ") ---");
        Funcion existente = funcionService.getById(id);
        if (existente == null) {
            System.out.println("No existe función con ID " + id);
            return;
        }

        String nuevaFecha = FormTypeValidator.readStringOptional("Ingrese la nueva Fecha (AAAA-MM-DD)", existente.getFecha());
        String nuevaHora = FormTypeValidator.readStringOptional("Ingrese la nueva Hora (HH:MM)", existente.getHora());

        Pelicula peliculaFinal = (nuevaPelicula != null) ? nuevaPelicula : existente.getPelicula();
        Sala salaFinal = (nuevaSala != null) ? nuevaSala : existente.getSala();

        Funcion actualizada = new Funcion(id, peliculaFinal, salaFinal, nuevaFecha, nuevaHora);
        funcionService.update(id, actualizada);
        System.out.println("Función actualizada con éxito:");
        System.out.println(actualizada);
    }

    public void deleteFuncion() {
        System.out.println("\n--- ELIMINAR FUNCIÓN ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la función a eliminar: ");
        deleteFuncion(id);
    }

    public void deleteFuncion(int id) {
        boolean eliminada = funcionService.delete(id);
        if (eliminada) {
            System.out.println("Función con ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
