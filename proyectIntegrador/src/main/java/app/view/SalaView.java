package app.view;

import app.domain.Sala;
import app.domain.enums.TipoSala;
import app.service.dto.SalaResponseDTO;
import app.service.helpers.JsonHelper;
import app.service.helpers.SetPropertyHelper;
import app.service.inputports.SalaServiceInterface;
import app.service.mappers.SalaDTOMapper;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class SalaView {
    private final SalaServiceInterface salaService;

    public SalaView(SalaServiceInterface salaService) {
        this.salaService = salaService;
    }

    public void createSala() {
        System.out.println("\n--- REGISTRAR SALA ---");
        String nombre = FormTypeValidator.readString("Ingrese el Nombre de la sala (ej: Sala 1, Sala Principal): ");
        int capacidad = FormTypeValidator.readInt("Ingrese la Capacidad de asientos: ");

        TipoSala tipoSala = SetPropertyHelper.setTipoSala();
        String tipo = tipoSala.getDescripcion();
        double recargo = tipoSala.getRecargo();

        Sala creada = salaService.create(nombre, capacidad, tipo, recargo);
        SalaResponseDTO dto = SalaDTOMapper.toResponseDTO(creada);
        System.out.println("Sala registrada con éxito:");
        System.out.println(JsonHelper.toJson(dto));
    }

    public void selectSalaById() {
        System.out.println("\n--- CONSULTAR SALA POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la sala a buscar: ");
        selectSalaById(id);
    }

    public void selectSalaById(int id) {
        Sala sala = salaService.getById(id);
        if (sala != null) {
            SalaResponseDTO dto = SalaDTOMapper.toResponseDTO(sala);
            System.out.println("Sala encontrada:");
            System.out.println(JsonHelper.toJson(dto));
        } else {
            System.out.println("No se encontró ninguna sala con ID " + id);
        }
    }

    public void selectAllSalas() {
        System.out.println("\n--- LISTA DE SALAS ---");
        List<Sala> salas = salaService.getAll();
        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas.");
        } else {
            List<SalaResponseDTO> dtos = SalaDTOMapper.toResponseDTOList(salas);
            System.out.println(JsonHelper.toJson(dtos));
        }
    }

    public void updateSala() {
        System.out.println("\n--- ACTUALIZAR SALA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la sala que desea actualizar: ");
        Sala existente = salaService.getById(id);
        if (existente == null) {
            System.out.println("No existe sala con ID " + id);
            return;
        }

        String nuevoNombre = FormTypeValidator.readStringOptional("Ingrese el nuevo Nombre", existente.getNombre());
        int nuevaCapacidad = FormTypeValidator.readIntOptional("Ingrese la nueva Capacidad", existente.getCapacidad());

        System.out.println("¿Desea cambiar el Tipo de Sala? (Actual: " + existente.getTipo() + ")");
        boolean cambiarTipo = FormTypeValidator.readBoolean("¿Desea seleccionar un nuevo tipo?");
        String nuevoTipo = existente.getTipo();
        double nuevoRecargo = existente.getRecargo();
        if (cambiarTipo) {
            TipoSala tipoSala = SetPropertyHelper.setTipoSala();
            nuevoTipo = tipoSala.getDescripcion();
            nuevoRecargo = tipoSala.getRecargo();
        }

        Sala salaActualizada = new Sala(id, nuevoNombre, nuevaCapacidad, nuevoTipo, nuevoRecargo);
        salaService.update(id, salaActualizada);
        SalaResponseDTO dto = SalaDTOMapper.toResponseDTO(salaActualizada);
        System.out.println("Sala actualizada con éxito:");
        System.out.println(JsonHelper.toJson(dto));
    }

    public void deleteSala() {
        System.out.println("\n--- ELIMINAR SALA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la sala que desea eliminar: ");
        deleteSala(id);
    }

    public void deleteSala(int id) {
        boolean eliminada = salaService.delete(id);
        if (eliminada) {
            System.out.println("Sala con ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
