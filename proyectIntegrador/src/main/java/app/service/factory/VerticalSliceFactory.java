package app.service.factory;

import app.domain.*;
import app.domain.enums.*;
import app.repository.config.AppConfig;
import app.repository.factory.RepositoryFactory;
import app.service.*;
import app.service.helpers.SetPropertyHelper;
import app.service.inputports.*;
import app.service.ouputports.*;
import app.service.validations.FormTypeValidator;
import app.view.*;

/**
 * Factory de Verticales (Vertical Slice Factory)
 * Orquesta la composición de cada vertical (Repository -> Service -> View)
 * e inicia el bucle interactivo gobernado por el Enum de menú específico de cada clase.
 */
public class VerticalSliceFactory {

    public static void ejecutar(ModuloSistema modulo) {
        if (modulo == null) return;

        switch (modulo) {
            case CLIENTES -> ejecutarModuloCliente();
            case PERSONAS -> ejecutarModuloPersona();
            case PELICULAS -> ejecutarModuloPelicula();
            case SALAS -> ejecutarModuloSala();
            case FUNCIONES -> ejecutarModuloFuncion();
            case BOLETOS -> ejecutarModuloBoleto();
            case RESERVAS -> ejecutarModuloReserva();
            case FACTURAS -> ejecutarModuloFactura();
            case CAMBIAR_AMBIENTE -> configurarAmbiente();
            case SALIR -> System.out.println("Saliendo del sistema...");
        }
    }

    private static void ejecutarModuloCliente() {
        ClienteRepositoryPort repo = RepositoryFactory.createClienteRepository();
        ClienteServiceInterface service = new ClienteServiceImp(repo);
        ClienteView view = new ClienteView(service);

        boolean activo = true;
        while (activo) {
            OpcionMenuCliente opcion = SetPropertyHelper.setOpcionMenuCliente();
            switch (opcion) {
                case REGISTRAR -> view.createCliente();
                case CONSULTAR_POR_ID -> view.selectClienteById();
                case LISTAR_TODOS -> view.selectAllClientes();
                case ACTUALIZAR -> view.updateCliente();
                case ELIMINAR -> view.deleteCliente();
                case SALIR -> {
                    System.out.println("\nRegresando al Menú Principal...");
                    activo = false;
                }
            }
        }
    }

    private static void ejecutarModuloPersona() {
        PersonaRepositoryPort repo = RepositoryFactory.createPersonaRepository();
        PersonaServiceInterface service = new PersonaServiceImp(repo);
        PersonaView view = new PersonaView(service);

        boolean activo = true;
        while (activo) {
            OpcionMenuPersona opcion = SetPropertyHelper.setOpcionMenuPersona();
            switch (opcion) {
                case REGISTRAR -> view.createPersona();
                case CONSULTAR_POR_ID -> view.selectPersonaById();
                case LISTAR_TODOS -> view.selectAllPersonas();
                case ACTUALIZAR -> view.updatePersona();
                case ELIMINAR -> view.deletePersona();
                case SALIR -> {
                    System.out.println("\nRegresando al Menú Principal...");
                    activo = false;
                }
            }
        }
    }

    private static void ejecutarModuloPelicula() {
        PeliculaRepositoryPort repo = RepositoryFactory.createPeliculaRepository();
        PeliculaServiceInterface service = new PeliculaServiceImp(repo);
        PeliculaView view = new PeliculaView(service);

        boolean activo = true;
        while (activo) {
            OpcionMenuPelicula opcion = SetPropertyHelper.setOpcionMenuPelicula();
            switch (opcion) {
                case REGISTRAR -> view.createPelicula();
                case CONSULTAR_POR_ID -> view.selectPeliculaById();
                case LISTAR_TODOS -> view.selectAllPeliculas();
                case ACTUALIZAR -> view.updatePelicula();
                case ELIMINAR -> view.deletePelicula();
                case SALIR -> {
                    System.out.println("\nRegresando al Menú Principal...");
                    activo = false;
                }
            }
        }
    }

    private static void ejecutarModuloSala() {
        SalaRepositoryPort repo = RepositoryFactory.createSalaRepository();
        SalaServiceInterface service = new SalaServiceImp(repo);
        SalaView view = new SalaView(service);

        boolean activo = true;
        while (activo) {
            OpcionMenuSala opcion = SetPropertyHelper.setOpcionMenuSala();
            switch (opcion) {
                case REGISTRAR -> view.createSala();
                case CONSULTAR_POR_ID -> view.selectSalaById();
                case LISTAR_TODOS -> view.selectAllSalas();
                case ACTUALIZAR -> view.updateSala();
                case ELIMINAR -> view.deleteSala();
                case SALIR -> {
                    System.out.println("\nRegresando al Menú Principal...");
                    activo = false;
                }
            }
        }
    }

    private static void ejecutarModuloFuncion() {
        FuncionRepositoryPort repo = RepositoryFactory.createFuncionRepository();
        FuncionServiceInterface service = new FuncionServiceImp(repo);
        FuncionView view = new FuncionView(service);

        PeliculaServiceInterface peliculaService = new PeliculaServiceImp(RepositoryFactory.createPeliculaRepository());
        SalaServiceInterface salaService = new SalaServiceImp(RepositoryFactory.createSalaRepository());

        boolean activo = true;
        while (activo) {
            OpcionMenuFuncion opcion = SetPropertyHelper.setOpcionMenuFuncion();
            switch (opcion) {
                case PROGRAMAR -> {
                    int idPelicula = FormTypeValidator.readInt("Ingrese el ID de la Película: ");
                    Pelicula pelicula = peliculaService.getById(idPelicula);
                    if (pelicula == null) {
                        System.out.println("Error: No existe película con ID " + idPelicula);
                        break;
                    }
                    int idSala = FormTypeValidator.readInt("Ingrese el ID de la Sala: ");
                    Sala sala = salaService.getById(idSala);
                    if (sala == null) {
                        System.out.println("Error: No existe sala con ID " + idSala);
                        break;
                    }
                    view.createFuncion(pelicula, sala);
                }
                case CONSULTAR_POR_ID -> view.selectFuncionById();
                case LISTAR_TODOS -> view.selectAllFunciones();
                case ACTUALIZAR -> {
                    int id = FormTypeValidator.readInt("Ingrese el ID de la función a actualizar: ");
                    view.updateFuncion(id, null, null);
                }
                case ELIMINAR -> view.deleteFuncion();
                case SALIR -> {
                    System.out.println("\nRegresando al Menú Principal...");
                    activo = false;
                }
            }
        }
    }

    private static void ejecutarModuloBoleto() {
        BoletoRepositoryPort repo = RepositoryFactory.createBoletoRepository();
        BoletoServiceInterface service = new BoletoServiceImp(repo);
        BoletoView view = new BoletoView(service);

        FuncionServiceInterface funcionService = new FuncionServiceImp(RepositoryFactory.createFuncionRepository());

        boolean activo = true;
        while (activo) {
            OpcionMenuBoleto opcion = SetPropertyHelper.setOpcionMenuBoleto();
            switch (opcion) {
                case EMITIR -> {
                    int idFuncion = FormTypeValidator.readInt("Ingrese el ID de la Función: ");
                    Funcion funcion = funcionService.getById(idFuncion);
                    if (funcion == null) {
                        System.out.println("Error: No existe función con ID " + idFuncion);
                        break;
                    }
                    view.createBoleto(funcion);
                }
                case CONSULTAR_POR_ID -> view.selectBoletoById();
                case LISTAR_TODOS -> view.selectAllBoletos();
                case ACTUALIZAR -> {
                    int id = FormTypeValidator.readInt("Ingrese el ID del boleto a actualizar: ");
                    view.updateBoleto(id, null);
                }
                case ELIMINAR -> view.deleteBoleto();
                case SALIR -> {
                    System.out.println("\nRegresando al Menú Principal...");
                    activo = false;
                }
            }
        }
    }

    private static void ejecutarModuloReserva() {
        ReservaRepositoryPort repo = RepositoryFactory.createReservaRepository();
        ReservaServiceInterface service = new ReservaServiceImp(repo);
        ReservaView view = new ReservaView(service);

        ClienteServiceInterface clienteService = new ClienteServiceImp(RepositoryFactory.createClienteRepository());
        FuncionServiceInterface funcionService = new FuncionServiceImp(RepositoryFactory.createFuncionRepository());

        boolean activo = true;
        while (activo) {
            OpcionMenuReserva opcion = SetPropertyHelper.setOpcionMenuReserva();
            switch (opcion) {
                case REGISTRAR -> {
                    int idCliente = FormTypeValidator.readInt("Ingrese el ID del Cliente: ");
                    Cliente cliente = clienteService.getById(idCliente);
                    if (cliente == null) {
                        System.out.println("Error: No existe cliente con ID " + idCliente);
                        break;
                    }
                    int idFuncion = FormTypeValidator.readInt("Ingrese el ID de la Función: ");
                    Funcion funcion = funcionService.getById(idFuncion);
                    if (funcion == null) {
                        System.out.println("Error: No existe función con ID " + idFuncion);
                        break;
                    }
                    view.createReserva(cliente, funcion);
                }
                case CONSULTAR_POR_ID -> view.selectReservaById();
                case LISTAR_TODOS -> view.selectAllReservas();
                case ACTUALIZAR -> {
                    int id = FormTypeValidator.readInt("Ingrese el ID de la reserva a actualizar: ");
                    view.updateReserva(id, null, null);
                }
                case ELIMINAR -> view.deleteReserva();
                case SALIR -> {
                    System.out.println("\nRegresando al Menú Principal...");
                    activo = false;
                }
            }
        }
    }

    private static void ejecutarModuloFactura() {
        FacturaRepositoryPort repo = RepositoryFactory.createFacturaRepository();
        FacturaServiceInterface service = new FacturaServiceImp(repo);
        FacturaView view = new FacturaView(service);

        ReservaServiceInterface reservaService = new ReservaServiceImp(RepositoryFactory.createReservaRepository());

        boolean activo = true;
        while (activo) {
            OpcionMenuFactura opcion = SetPropertyHelper.setOpcionMenuFactura();
            switch (opcion) {
                case GENERAR -> {
                    int idReserva = FormTypeValidator.readInt("Ingrese el ID de la Reserva para facturar: ");
                    Reserva reserva = reservaService.getById(idReserva);
                    if (reserva == null) {
                        System.out.println("Error: No existe reserva con ID " + idReserva);
                        break;
                    }
                    view.createFactura(reserva);
                }
                case CONSULTAR_POR_ID -> view.selectFacturaById();
                case LISTAR_TODOS -> view.selectAllFacturas();
                case ACTUALIZAR -> {
                    int id = FormTypeValidator.readInt("Ingrese el ID de la factura a actualizar: ");
                    view.updateFactura(id, null);
                }
                case ELIMINAR -> view.deleteFactura();
                case SALIR -> {
                    System.out.println("\nRegresando al Menú Principal...");
                    activo = false;
                }
            }
        }
    }

    private static void configurarAmbiente() {
        Ambiente nuevoAmbiente = SetPropertyHelper.setAmbiente();
        AppConfig.setAmbiente(nuevoAmbiente);
    }
}
