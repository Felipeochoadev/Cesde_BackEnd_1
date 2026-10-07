package app.view;

import app.domain.Boleto;
import app.domain.Funcion;
import app.service.inputports.BoletoServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class BoletoView {
    private final BoletoServiceInterface boletoService;

    public BoletoView(BoletoServiceInterface boletoService) {
        this.boletoService = boletoService;
    }

    public void createBoleto(Funcion funcion) {
        System.out.println("\n--- EMITIR BOLETO ---");
        String codigo = FormTypeValidator.readString("Ingrese el Código del boleto (ej: BOL-001): ");
        String asiento = FormTypeValidator.readString("Ingrese el Asiento (ej: F5, A1): ");

        Boleto creado = boletoService.create(codigo, funcion, asiento);
        System.out.println("Boleto emitido con éxito:");
        System.out.println(creado);
    }

    public void selectBoletoById() {
        System.out.println("\n--- CONSULTAR BOLETO POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del boleto a buscar: ");
        selectBoletoById(id);
    }

    public void selectBoletoById(int id) {
        Boleto boleto = boletoService.getById(id);
        if (boleto != null) {
            System.out.println("Boleto encontrado:");
            System.out.println(boleto);
        } else {
            System.out.println("No se encontró ningún boleto con ID " + id);
        }
    }

    public void selectAllBoletos() {
        System.out.println("\n--- LISTA DE BOLETOS EMITIDOS ---");
        List<Boleto> boletos = boletoService.getAll();
        if (boletos.isEmpty()) {
            System.out.println("No hay boletos emitidos actualmente.");
        } else {
            for (Boleto b : boletos) {
                System.out.println(b);
            }
        }
    }

    public void updateBoleto(int id, Funcion funcionActualizada) {
        System.out.println("\n--- ACTUALIZAR BOLETO ID (" + id + ") ---");
        Boleto existente = boletoService.getById(id);
        if (existente == null) {
            System.out.println("No existe boleto con ID " + id);
            return;
        }

        String nuevoCodigo = FormTypeValidator.readStringOptional("Ingrese el nuevo Código del boleto", existente.getCodigo());
        String nuevoAsiento = FormTypeValidator.readStringOptional("Ingrese el nuevo Asiento", existente.getAsiento());
        Funcion funcionFinal = (funcionActualizada != null) ? funcionActualizada : existente.getFuncion();

        Boleto actualizado = new Boleto(id, nuevoCodigo, funcionFinal, nuevoAsiento);
        boletoService.update(id, actualizado);
        System.out.println("Boleto actualizado con éxito:");
        System.out.println(actualizado);
    }

    public void deleteBoleto() {
        System.out.println("\n--- ELIMINAR BOLETO ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del boleto a eliminar: ");
        deleteBoleto(id);
    }

    public void deleteBoleto(int id) {
        boolean eliminado = boletoService.delete(id);
        if (eliminado) {
            System.out.println("Boleto con ID " + id + " eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
