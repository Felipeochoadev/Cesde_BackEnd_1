package app.view;

import app.domain.Sala;
import app.service.inputports.SalaServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class SalaView {
    private final SalaServiceInterface salaService;

    public SalaView(SalaServiceInterface salaService) {
        this.salaService = salaService;
    }

    public void createSala() {
        System.out.println("\n--- REGISTRAR SALA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la sala: ");
        String nombre = FormTypeValidator.readString("Ingrese el Nombre de la sala (ej: Sala 1, Sala VIP): ");
        int capacidad = FormTypeValidator.readInt("Ingrese la Capacidad de asientos: ");
        String tipo = FormTypeValidator.readString("Ingrese el Tipo de sala (2D, 3D, VIP, IMAX): ");
        double recargo = FormTypeValidator.readDouble("Ingrese el Recargo adicional por tipo de sala: ");

        // Paso 14 y 15: Pasa los parámetros capturados al create del servicio
        Sala creada = salaService.create(id, nombre, capacidad, tipo, recargo);
        System.out.println("✅ Sala registrada con éxito: " + creada);
    }

    public void selectSalaById() {
        System.out.println("\n--- CONSULTAR SALA POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la sala a buscar: ");
        selectSalaById(id);
    }

    public void selectSalaById(int id) {
        Sala sala = salaService.getById(id);
        if (sala != null) {
            System.out.println("Encontrada: " + sala + " (Recargo: $" + sala.getRecargo() + ")");
        } else {
            System.out.println("❌ No se encontró ninguna sala con ID " + id);
        }
    }

    public void selectAllSalas() {
        System.out.println("\n--- LISTA DE SALAS ---");
        List<Sala> salas = salaService.getAll();
        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas.");
        } else {
            for (Sala s : salas) {
                System.out.println("• " + s);
            }
        }
    }

    public void updateSala() {
        System.out.println("\n--- ACTUALIZAR SALA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la sala que desea actualizar: ");
        Sala existente = salaService.getById(id);
        if (existente == null) {
            System.out.println("❌ No existe sala con ID " + id);
            return;
        }

        String nuevoNombre = FormTypeValidator.readString("Ingrese el nuevo Nombre: ");
        int nuevaCapacidad = FormTypeValidator.readInt("Ingrese la nueva Capacidad: ");
        String nuevoTipo = FormTypeValidator.readString("Ingrese el nuevo Tipo: ");
        double nuevoRecargo = FormTypeValidator.readDouble("Ingrese el nuevo Recargo: ");

        Sala salaActualizada = new Sala(id, nuevoNombre, nuevaCapacidad, nuevoTipo, nuevoRecargo);
        salaService.update(id, salaActualizada);
        System.out.println("✅ Sala actualizada con éxito.");
    }

    public void deleteSala() {
        System.out.println("\n--- ELIMINAR SALA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la sala que desea eliminar: ");
        deleteSala(id);
    }

    public void deleteSala(int id) {
        boolean eliminada = salaService.delete(id);
        if (eliminada) {
            System.out.println("✅ Sala con ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
