package app.main;

import app.domain.*;

public class App {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CINE - CESDE ===\n");

        Pelicula peli1 = new Pelicula(1, "Deadpool & Wolverine", "Acción", 127, 15000.0, 18);
        Pelicula peli2 = new Pelicula(2, "Intensamente 2", "Animación", 100, 12000.0, 0);

        Sala sala1 = new Sala(1, "Sala 1", 50, "2D", 0.0);
        Sala salaVIP = new Sala(2, "Sala VIP", 30, "VIP", 5000.0);

        Funcion funcion1 = new Funcion(101, peli1, salaVIP, "2026-06-05", "20:00");
        Funcion funcion2 = new Funcion(102, peli2, sala1, "2026-06-05", "16:00");

        Cliente clienteAdulto = new Cliente(1, "Felipe Ochoa", "felipe@cesde.edu.co", "3001234567", 25);
        Cliente clienteMenor = new Cliente(2, "Mateo Gomez", "mateo@gmail.com", "3119876543", 14);
        Cliente clienteNino = new Cliente(3, "Sofia Restrepo", "sofia@gmail.com", "3204567890", 8);

        System.out.println("--- CASO 1: Compra de adulto para función VIP ---");
        Reserva reserva1 = new Reserva(1, clienteAdulto, funcion1, 2);
        Factura factura1 = new Factura(1, "FAC-001", reserva1, "Tarjeta");
        factura1.pagar();
        factura1.imprimirFactura();

        System.out.println("--- CASO 2: Intento de compra de un menor a película +18 ---");
        Reserva reservaMenor = new Reserva(2, clienteMenor, funcion1, 1);
        System.out.println("¿El cliente puede ver la película?: " + reservaMenor.esValidaPorEdad());
        reservaMenor.confirmar();
        System.out.println();

        System.out.println("--- CASO 3: Compra con descuento para niño (menor de 12 años) ---");
        Reserva reservaNino = new Reserva(3, clienteNino, funcion2, 2);
        Factura facturaNino = new Factura(2, "FAC-002", reservaNino, "Nequi");
        facturaNino.pagar();
        facturaNino.imprimirFactura();

        System.out.println("--- CASO 4: Compra con descuento por grupo (> 4 boletos) ---");
        Reserva reservaGrupo = new Reserva(4, clienteAdulto, funcion2, 5);
        Factura facturaGrupo = new Factura(3, "FAC-003", reservaGrupo, "Efectivo");
        facturaGrupo.pagar();
        facturaGrupo.imprimirFactura();
    }
}
