package app.view;

import app.domain.Factura;
import app.domain.Reserva;
import app.domain.enums.MetodoPago;
import app.service.helpers.SetPropertyHelper;
import app.service.inputports.FacturaServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class FacturaView {
    private final FacturaServiceInterface facturaService;

    public FacturaView(FacturaServiceInterface facturaService) {
        this.facturaService = facturaService;
    }

    public void createFactura(Reserva reserva) {
        System.out.println("\n--- GENERAR FACTURA DE VENTA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la factura: ");
        String numero = FormTypeValidator.readString("Ingrese el Número de factura (ej: FAC-001): ");

        // 🌟 Uso de SetPropertyHelper: Menú para seleccionar método de pago
        MetodoPago metodo = SetPropertyHelper.setMetodoPago();
        String metodoPago = metodo.getNombre();

        // Paso 14 y 15: Pasa los parámetros capturados al create del servicio
        Factura factura = facturaService.create(id, numero, reserva, metodoPago);
        factura.pagar();

        System.out.println("✅ Factura generada y pagada con éxito.");
        factura.imprimirFactura();
    }

    public void selectFacturaById() {
        System.out.println("\n--- CONSULTAR FACTURA POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la factura a buscar: ");
        selectFacturaById(id);
    }

    public void selectFacturaById(int id) {
        Factura factura = facturaService.getById(id);
        if (factura != null) {
            System.out.println("Encontrada: " + factura);
            factura.imprimirFactura();
        } else {
            System.out.println("❌ No se encontró ninguna factura con ID " + id);
        }
    }

    public void selectAllFacturas() {
        System.out.println("\n--- HISTORIAL DE FACTURAS REGISTRADAS ---");
        List<Factura> facturas = facturaService.getAll();
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas en el sistema.");
        } else {
            for (Factura f : facturas) {
                System.out.println("• " + f);
            }
        }
    }

    public void updateFactura(int id, Reserva nuevaReserva) {
        System.out.println("\n--- ACTUALIZAR FACTURA ID (" + id + ") ---");
        String nuevoNumero = FormTypeValidator.readString("Ingrese el nuevo Número de factura: ");

        // 🌟 Uso de SetPropertyHelper en actualización
        MetodoPago metodo = SetPropertyHelper.setMetodoPago();
        String nuevoMetodo = metodo.getNombre();

        Factura actualizada = new Factura(id, nuevoNumero, nuevaReserva, nuevoMetodo);
        facturaService.update(id, actualizada);
        System.out.println("✅ Factura actualizada con éxito.");
    }

    public void deleteFactura() {
        System.out.println("\n--- ELIMINAR FACTURA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la factura a eliminar: ");
        deleteFactura(id);
    }

    public void deleteFactura(int id) {
        boolean eliminada = facturaService.delete(id);
        if (eliminada) {
            System.out.println("✅ Factura con ID " + id + " eliminada del historial.");
        } else {
            System.out.println("❌ No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
