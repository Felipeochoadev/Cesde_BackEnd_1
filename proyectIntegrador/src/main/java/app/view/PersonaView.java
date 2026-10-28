package app.view;

import app.domain.Persona;
import app.service.dto.PersonaResponseDTO;
import app.service.helpers.JsonHelper;
import app.service.inputports.PersonaServiceInterface;
import app.service.mappers.PersonaDTOMapper;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class PersonaView {
    private final PersonaServiceInterface personaService;

    public PersonaView(PersonaServiceInterface personaService) {
        this.personaService = personaService;
    }

    public void createPersona() {
        System.out.println("\n--- REGISTRAR PERSONA ---");
        String nombre = FormTypeValidator.readString("Ingrese el Nombre: ");
        String correo = FormTypeValidator.readString("Ingrese el Correo electrónico: ");
        String telefono = FormTypeValidator.readString("Ingrese el Teléfono: ");

        Persona creada = personaService.create(nombre, correo, telefono);
        PersonaResponseDTO dto = PersonaDTOMapper.toResponseDTO(creada);
        System.out.println("Persona registrada con éxito:");
        System.out.println(JsonHelper.toJson(dto));
    }

    public void selectPersonaById() {
        System.out.println("\n--- CONSULTAR PERSONA POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la persona a buscar: ");
        selectPersonaById(id);
    }

    public void selectPersonaById(int id) {
        Persona persona = personaService.getById(id);
        if (persona != null) {
            PersonaResponseDTO dto = PersonaDTOMapper.toResponseDTO(persona);
            System.out.println("Persona encontrada:");
            System.out.println(JsonHelper.toJson(dto));
        } else {
            System.out.println("No se encontró ninguna persona con ID " + id);
        }
    }

    public void selectAllPersonas() {
        System.out.println("\n--- LISTA DE PERSONAS REGISTRADAS ---");
        List<Persona> personas = personaService.getAll();
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas actualmente.");
        } else {
            List<PersonaResponseDTO> dtos = PersonaDTOMapper.toResponseDTOList(personas);
            System.out.println(JsonHelper.toJson(dtos));
        }
    }

    public void updatePersona() {
        System.out.println("\n--- ACTUALIZAR PERSONA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la persona que desea actualizar: ");
        Persona existente = personaService.getById(id);
        if (existente == null) {
            System.out.println("No existe persona con ID " + id);
            return;
        }

        String nuevoNombre = FormTypeValidator.readStringOptional("Ingrese el nuevo Nombre", existente.getNombre());
        String nuevoCorreo = FormTypeValidator.readStringOptional("Ingrese el nuevo Correo", existente.getCorreo());
        String nuevoTelefono = FormTypeValidator.readStringOptional("Ingrese el nuevo Teléfono", existente.getTelefono());

        Persona personaActualizada = new Persona(id, nuevoNombre, nuevoCorreo, nuevoTelefono);
        personaService.update(id, personaActualizada);
        PersonaResponseDTO dto = PersonaDTOMapper.toResponseDTO(personaActualizada);
        System.out.println("Persona actualizada con éxito:");
        System.out.println(JsonHelper.toJson(dto));
    }

    public void deletePersona() {
        System.out.println("\n--- ELIMINAR PERSONA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la persona que desea eliminar: ");
        deletePersona(id);
    }

    public void deletePersona(int id) {
        boolean eliminada = personaService.delete(id);
        if (eliminada) {
            System.out.println("Persona con ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
