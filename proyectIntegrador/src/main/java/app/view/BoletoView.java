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
        int id = FormTypeValidator.readInt("Ingrese el ID del boleto: ");
        String codigo = FormTypeValidator.readString("Ingrese el Código del boleto (ej: BOL-001): ");
        String asiento = FormTypeValidator.readString("Ingrese el Asiento (ej: F5, A1): ");

        // Paso 14 y 15: Pasa los parámetros capturados al create del servicio
        Boleto creado = boletoService.create(id, codigo, funcion, asiento);
        System.out.println("✅ Boleto emitido con éxito: " + creado);
    }

    public void selectBoletoById() {
        System.out.println("\n--- CONSULTAR BOLETO POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del boleto a buscar: ");
        selectBoletoById(id);
    }

    public void selectBoletoById(int id) {
        Boleto boleto = boletoService.getById(id);
        if (boleto != null) {
            System.out.println("Encontrado: " + boleto);
        } else {
            System.out.println("❌ No se encontró ningún boleto con ID " + id);
        }
    }

    public void selectAllBoletos() {
        System.out.println("\n--- LISTA DE BOLETOS EMITIDOS ---");
        List<Boleto> boletos = boletoService.getAll();
        if (boletos.isEmpty()) {
            System.out.println("No hay boletos emitidos actualmente.");
        } else {
            for (Boleto b : boletos) {
                System.out.println("• " + b);
            }
        }
    }

    public void updateBoleto(int id, Funcion funcionActualizada) {
        System.out.println("\n--- ACTUALIZAR BOLETO ID (" + id + ") ---");
        String nuevoCodigo = FormTypeValidator.readString("Ingrese el nuevo Código del boleto: ");
        String nuevoAsiento = FormTypeValidator.readString("Ingrese el nuevo Asiento: ");

        Boleto actualizado = new Boleto(id, nuevoCodigo, funcionActualizada, nuevoAsiento);
        boletoService.update(id, actualizado);
        System.out.println("✅ Boleto actualizado con éxito.");
    }

    public void deleteBoleto() {
        System.out.println("\n--- ELIMINAR BOLETO ---");
        int id = FormTypeValidator.readInt("Ingrese el ID del boleto a eliminar: ");
        deleteBoleto(id);
    }

    public void deleteBoleto(int id) {
        boolean eliminado = boletoService.delete(id);
        if (eliminado) {
            System.out.println("✅ Boleto con ID " + id + " eliminado correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
