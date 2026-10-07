package app;

import app.domain.enums.OpcionMenuCliente;
import app.repository.ClienteRepositoryImp;
import app.service.ClienteServiceImp;
import app.service.helpers.SetPropertyHelper;
import app.service.inputports.ClienteServiceInterface;
import app.service.ouputports.ClienteRepositoryPort;
import app.view.ClienteView;

public class CineMaxCesdeApplication {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CINE - CINEMAX CESDE ===");

        // 1. Repositorio (Puerto de salida / Persistencia)
        ClienteRepositoryPort clienteRepository = new ClienteRepositoryImp();

        // 2. Servicio (Puerto de entrada / Lógica) inyectándole el repositorio
        ClienteServiceInterface clienteService = new ClienteServiceImp(clienteRepository);

        // 3. Vista (Consola / Interfaz) inyectándole el servicio
        ClienteView clienteView = new ClienteView(clienteService);

        // 4. Bucle interactivo con Switch y Enum Helper
        boolean ejecutando = true;

        while (ejecutando) {
            OpcionMenuCliente opcion = SetPropertyHelper.setOpcionMenuCliente();

            switch (opcion) {
                case REGISTRAR:
                    clienteView.createCliente();
                    break;
                case CONSULTAR_POR_ID:
                    clienteView.selectClienteById();
                    break;
                case LISTAR_TODOS:
                    clienteView.selectAllClientes();
                    break;
                case ACTUALIZAR:
                    clienteView.updateCliente();
                    break;
                case ELIMINAR:
                    clienteView.deleteCliente();
                    break;
                case SALIR:
                    System.out.println("\nSaliendo del sistema de gestión de clientes. ¡Hasta pronto!");
                    ejecutando = false;
                    break;
            }
        }
    }
}
