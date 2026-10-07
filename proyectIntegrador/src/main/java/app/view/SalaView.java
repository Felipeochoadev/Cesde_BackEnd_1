package app.view;

import app.domain.Sala;
import app.domain.enums.TipoSala;
import app.service.helpers.SetPropertyHelper;
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
        String nombre = FormTypeValidator.readString("Ingrese el Nombre de la sala (ej: Sala 1, Sala Principal): ");
        int capacidad = FormTypeValidator.readInt("Ingrese la Capacidad de asientos: ");

        // 🌟 Uso de SetPropertyHelper: Menú para elegir tipo y obtener recargo automáticamente
        TipoSala tipoSala = SetPropertyHelper.setTipoSala();
        String tipo = tipoSala.getDescripcion();
        double recargo = tipoSala.getRecargo();

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

        // 🌟 Uso de SetPropertyHelper en actualización
        TipoSala tipoSala = SetPropertyHelper.setTipoSala();
        String nuevoTipo = tipoSala.getDescripcion();
        double nuevoRecargo = tipoSala.getRecargo();

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
