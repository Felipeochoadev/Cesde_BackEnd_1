package app.view;

import app.domain.Persona;
import app.service.inputports.PersonaServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class PersonaView {
    private final PersonaServiceInterface personaService;

    public PersonaView(PersonaServiceInterface personaService) {
        this.personaService = personaService;
    }

    /**
     * Paso 13 y 14: Captura los datos con FormTypeValidator y llama al servicio create()
     */
    public void createPersona() {
        System.out.println("\n--- REGISTRAR PERSONA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la persona: ");
        String nombre = FormTypeValidator.readString("Ingrese el Nombre: ");
        String correo = FormTypeValidator.readString("Ingrese el Correo electrónico: ");
        String telefono = FormTypeValidator.readString("Ingrese el Teléfono: ");

        Persona creada = personaService.create(id, nombre, correo, telefono);
        System.out.println("✅ Persona creada y guardada con éxito en el sistema: " + creada);
    }

    public void selectPersonaById() {
        System.out.println("\n--- CONSULTAR PERSONA POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la persona a buscar: ");
        selectPersonaById(id);
    }

    public void selectPersonaById(int id) {
        Persona persona = personaService.getById(id);
        if (persona != null) {
            System.out.println("Encontrada: " + persona);
        } else {
            System.out.println("❌ No se encontró ninguna persona con ID " + id);
        }
    }

    public void selectAllPersonas() {
        System.out.println("\n--- LISTA DE PERSONAS REGISTRADAS ---");
        List<Persona> personas = personaService.getAll();
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas actualmente.");
        } else {
            for (Persona p : personas) {
                System.out.println("• " + p);
            }
        }
    }

    public void updatePersona() {
        System.out.println("\n--- ACTUALIZAR PERSONA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la persona que desea actualizar: ");
        Persona existente = personaService.getById(id);
        if (existente == null) {
            System.out.println("❌ No existe persona con ID " + id);
            return;
        }

        String nuevoNombre = FormTypeValidator.readString("Ingrese el nuevo Nombre: ");
        String nuevoCorreo = FormTypeValidator.readString("Ingrese el nuevo Correo: ");
        String nuevoTelefono = FormTypeValidator.readString("Ingrese el nuevo Teléfono: ");

        Persona personaActualizada = new Persona(id, nuevoNombre, nuevoCorreo, nuevoTelefono);
        personaService.update(id, personaActualizada);
        System.out.println("✅ Persona actualizada con éxito.");
    }

    public void deletePersona() {
        System.out.println("\n--- ELIMINAR PERSONA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la persona que desea eliminar: ");
        deletePersona(id);
    }

    public void deletePersona(int id) {
        boolean eliminada = personaService.delete(id);
        if (eliminada) {
            System.out.println("✅ Persona con ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
