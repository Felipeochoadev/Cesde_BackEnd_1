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
        String nombre = FormTypeValidator.readString("Ingrese el Nombre: ");
        String correo = FormTypeValidator.readString("Ingrese el Correo electrónico: ");
        String telefono = FormTypeValidator.readString("Ingrese el Teléfono: ");
        int edad = FormTypeValidator.readInt("Ingrese la Edad: ");

        Cliente creado = clienteService.create(nombre, correo, telefono, edad);
        System.out.println("Cliente registrado con éxito:");
        System.out.println(creado);
    }

    public void selectClienteById() {
        System.out.println("\n--- CONSULTAR CLIENTE POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del cliente a buscar: ");
        selectClienteById(id);
    }

    public void selectClienteById(int id) {
        Cliente cliente = clienteService.getById(id);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("No se encontró ningún cliente con ID " + id);
        }
    }

    public void selectAllClientes() {
        System.out.println("\n--- LISTA DE CLIENTES REGISTRADOS ---");
        List<Cliente> clientes = clienteService.getAll();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    public void updateCliente() {
        System.out.println("\n--- ACTUALIZAR CLIENTE ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del cliente que desea actualizar: ");
        Cliente existente = clienteService.getById(id);
        if (existente == null) {
            System.out.println("No existe cliente con ID " + id);
            return;
        }

        String nuevoNombre = FormTypeValidator.readStringOptional("Ingrese el nuevo Nombre", existente.getNombre());
        String nuevoCorreo = FormTypeValidator.readStringOptional("Ingrese el nuevo Correo", existente.getCorreo());
        String nuevoTelefono = FormTypeValidator.readStringOptional("Ingrese el nuevo Teléfono", existente.getTelefono());
        int nuevaEdad = FormTypeValidator.readIntOptional("Ingrese la nueva Edad", existente.getEdad());

        Cliente clienteActualizado = new Cliente(id, nuevoNombre, nuevoCorreo, nuevoTelefono, nuevaEdad);
        clienteService.update(id, clienteActualizado);
        System.out.println("Cliente actualizado con éxito:");
        System.out.println(clienteActualizado);
    }

    public void deleteCliente() {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del cliente que desea eliminar: ");
        deleteCliente(id);
    }

    public void deleteCliente(int id) {
        boolean eliminado = clienteService.delete(id);
        if (eliminado) {
            System.out.println("Cliente con ID " + id + " eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
