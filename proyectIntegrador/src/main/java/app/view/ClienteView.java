package app.view;

import app.domain.Cliente;
import app.service.inputports.ClienteServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class ClienteView {
    private final ClienteServiceInterface clienteService;

    public ClienteView(ClienteServiceInterface clienteService) {
        this.clienteService = clienteService;
    }

    public void createCliente() {
        System.out.println("\n--- REGISTRAR CLIENTE ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del cliente: ");
        String nombre = FormTypeValidator.readString("Ingrese el Nombre: ");
        String correo = FormTypeValidator.readString("Ingrese el Correo electrónico: ");
        String telefono = FormTypeValidator.readString("Ingrese el Teléfono: ");
        int edad = FormTypeValidator.readInt("Ingrese la Edad: ");

        // Paso 14 y 15: Pasa los parámetros capturados al create del servicio
        Cliente creado = clienteService.create(id, nombre, correo, telefono, edad);
        System.out.println("✅ Cliente creado y guardado con éxito: " + creado);
    }

    public void selectClienteById() {
        System.out.println("\n--- CONSULTAR CLIENTE POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del cliente a buscar: ");
        selectClienteById(id);
    }

    public void selectClienteById(int id) {
        Cliente cliente = clienteService.getById(id);
        if (cliente != null) {
            System.out.println("Encontrado: " + cliente + " (Mayor de edad: " + cliente.esMayorDeEdad() + ")");
        } else {
            System.out.println("❌ No se encontró ningún cliente con ID " + id);
        }
    }

    public void selectAllClientes() {
        System.out.println("\n--- LISTA DE CLIENTES REGISTRADOS ---");
        List<Cliente> clientes = clienteService.getAll();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente c : clientes) {
                System.out.println("• " + c);
            }
        }
    }

    public void updateCliente() {
        System.out.println("\n--- ACTUALIZAR CLIENTE ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del cliente que desea actualizar: ");
        Cliente existente = clienteService.getById(id);
        if (existente == null) {
            System.out.println("❌ No existe cliente con ID " + id);
            return;
        }

        String nuevoNombre = FormTypeValidator.readString("Ingrese el nuevo Nombre: ");
        String nuevoCorreo = FormTypeValidator.readString("Ingrese el nuevo Correo: ");
        String nuevoTelefono = FormTypeValidator.readString("Ingrese el nuevo Teléfono: ");
        int nuevaEdad = FormTypeValidator.readInt("Ingrese la nueva Edad: ");

        Cliente clienteActualizado = new Cliente(id, nuevoNombre, nuevoCorreo, nuevoTelefono, nuevaEdad);
        clienteService.update(id, clienteActualizado);
        System.out.println("✅ Cliente actualizado con éxito.");
    }

    public void deleteCliente() {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del cliente que desea eliminar: ");
        deleteCliente(id);
    }

    public void deleteCliente(int id) {
        boolean eliminado = clienteService.delete(id);
        if (eliminado) {
            System.out.println("✅ Cliente con ID " + id + " eliminado correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
