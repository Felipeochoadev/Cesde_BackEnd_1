package app.main;

import app.domain.*;
import app.domain.enums.ClasificacionEdad;
import app.domain.enums.TipoPuesto;
import app.domain.strategy.DescuentoCantidadBoletos;
import app.domain.strategy.DescuentoCineMaxRegla;
import app.domain.strategy.DescuentoEdadNino;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase de Prueba y Demostración del Backend de CineMax CESDE.
 * Valida la arquitectura de Clases POO y los Principios SOLID aplicados.
 */
public class    App {
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("            CINEMAX CESDE - DEMOSTRACIÓN BACKEND POO Y SOLID                   ");
        System.out.println("================================================================================\n");

        // 1. CARGA DE CATÁLOGO BASE
        Categoria catAccion = new Categoria(1, "Acción", "Películas con secuencias intensas y combates");
        Categoria catAnimacion = new Categoria(2, "Animación", "Películas aptas para toda la familia en animación");
        Categoria catSciFi = new Categoria(3, "Ciencia Ficción", "Historias futuristas y viajes espaciales");

        Clasificacion clasifAdultos = new Clasificacion(1, ClasificacionEdad.MAYORES_18);
        Clasificacion clasifTodoPublico = new Clasificacion(2, ClasificacionEdad.TODOS);

        Formato formato2D = new Formato(1, "2D Tradicional", 0.0);
        Formato formato3D = new Formato(2, "3D MacroXE", 3000.0);
        Formato formatoVIP = new Formato(3, "VIP Premium", 5000.0);

        // Películas (Basadas en CineMaxCesde y Cesde-Logica)
        Pelicula peliDeadpool = new Pelicula(4, "Deadpool & Wolverine", 127, 17500.0, LocalDate.of(2026, 5, 13), catAccion, clasifAdultos);
        Pelicula peliInsideOut = new Pelicula(2, "Inside Out 2", 100, 13500.0, LocalDate.of(2026, 5, 13), catAnimacion, clasifTodoPublico);
        Pelicula peliInterstellar = new Pelicula(3, "Interstellar", 169, 17500.0, LocalDate.of(2026, 3, 15), catSciFi, clasifTodoPublico);

        // Salas y Puestos
        Sala sala1 = new Sala(1, "Sala 1 - General", 50, formato2D);
        Sala salaVIP = new Sala(3, "Sala 3 - VIP Premium", 30, formatoVIP);

        // Funciones
        Funcion funcDeadpoolNoche = new Funcion(101, peliDeadpool, salaVIP, LocalDate.of(2026, 6, 5), LocalTime.of(20, 0), LocalTime.of(22, 10));
        Funcion funcInsideOutTarde = new Funcion(102, peliInsideOut, sala1, LocalDate.of(2026, 6, 5), LocalTime.of(15, 30), LocalTime.of(17, 10));

        // -------------------------------------------------------------------------
        // ESCENARIO 1: Reserva y Facturación Exitosa (Cliente Adulto)
        // -------------------------------------------------------------------------
        System.out.println(">>> [ESCENARIO 1] Reserva exitosa para adulto (Felipe Ochoa, 35 años)");
        Cliente clienteFelipe = new Cliente(1, "Felipe Ochoa", "felipe.ochoa@cesde.edu.co", "3001234567", 35);
        Reserva reserva1 = new Reserva(1001, clienteFelipe, funcDeadpoolNoche, 2);

        // Asignación de puestos específicos
        Puesto puestoVIP1 = new Puesto(1, "F", 5, TipoPuesto.VIP);
        Puesto puestoVIP2 = new Puesto(2, "F", 6, TipoPuesto.VIP);
        reserva1.agregarBoleto(new Boleto(1, "TK-1001-A", funcDeadpoolNoche, puestoVIP1));
        reserva1.agregarBoleto(new Boleto(2, "TK-1001-B", funcDeadpoolNoche, puestoVIP2));

        // Inversión de dependencias (DIP) y OCP: Aplicación de descuento estándar CineMax
        reserva1.setDescuentoStrategy(new DescuentoCineMaxRegla());
        
        // Polimorfismo de métodos de pago (LSP)
        MetodoPago pagoTarjeta = new PagoTarjeta(1, "VISA", "4500123456789012", 1);
        Factura factura1 = new Factura(5001, "FAC-2026-0001", reserva1, pagoTarjeta);
        factura1.procesarPago();

        System.out.println(factura1.generarComprobanteTexto());

        // -------------------------------------------------------------------------
        // ESCENARIO 2: Validación de Regla de Negocio - Restricción de Edad (+18)
        // -------------------------------------------------------------------------
        System.out.println(">>> [ESCENARIO 2] Intento de reserva por menor de edad a película +18");
        Cliente clienteMenor = new Cliente(2, "Mateo Gomez", "mateo@gmail.com", "3119876543", 15);
        Reserva reservaMenor = new Reserva(1002, clienteMenor, funcDeadpoolNoche, 1);

        try {
            System.out.println("Intentando confirmar reserva para " + clienteMenor.getNombre() + " (Edad: " + clienteMenor.getEdad() + ") a " + peliDeadpool.getNombre() + "...");
            reservaMenor.confirmar();
            System.out.println("❌ Error: La reserva no debió permitirse.");
        } catch (IllegalStateException | IllegalArgumentException ex) {
            System.out.println("✅ Validación de Negocio Exitosa (Capturada por ValidadorReserva):");
            System.out.println("   " + ex.getMessage() + "\n");
        }

        // -------------------------------------------------------------------------
        // ESCENARIO 3: Aplicación de Descuento (Regla Cesde-Logica: boletos > 4 || edad < 12)
        // -------------------------------------------------------------------------
        System.out.println(">>> [ESCENARIO 3] Aplicación de Descuento del 10% (Familia con niño de 8 años)");
        Cliente clienteNino = new Cliente(3, "Sofia Restrepo", "sofia.papa@gmail.com", "3204567890", 8);
        Reserva reservaFamilia = new Reserva(1003, clienteNino, funcInsideOutTarde, 3);
        
        MetodoPago pagoNequi = new PagoDigital(2, "Nequi", "3204567890", "NQ-9847291");
        Factura facturaFamilia = new Factura(5002, "FAC-2026-0002", reservaFamilia, pagoNequi);
        facturaFamilia.procesarPago();

        System.out.println(facturaFamilia.generarComprobanteTexto());

        // -------------------------------------------------------------------------
        // ESCENARIO 4: Principio OCP - Cambio dinámico de Estrategia de Descuento
        // -------------------------------------------------------------------------
        System.out.println(">>> [ESCENARIO 4] Extensibilidad OCP con diferentes Estrategias de Descuento:");
        Cliente clienteAdultoGrupo = new Cliente(4, "Carlos Mendoza", "carlos@gmail.com", "3015554433", 28);
        Reserva reservaGrupo = new Reserva(1004, clienteAdultoGrupo, funcInsideOutTarde, 6);

        reservaGrupo.setDescuentoStrategy(new DescuentoCantidadBoletos(4, 0.15)); // Promoción especial: 15% para >4 boletos
        System.out.println("Subtotal para 6 boletos: $" + reservaGrupo.getSubtotal() + " COP");
        System.out.println("Estrategia activa: " + reservaGrupo.getDescuentoStrategy().getDescripcion());
        System.out.println("Descuento aplicado: -$" + reservaGrupo.getMontoDescuento() + " COP");
        System.out.println("Total a pagar: $" + reservaGrupo.calcularTotal() + " COP\n");

        System.out.println("================================================================================");
        System.out.println("        TODAS LAS PRUEBAS DE DOMINIO Y SOLID COMPLETADAS SATISFACTORIAMENTE      ");
        System.out.println("================================================================================");
    }
}
