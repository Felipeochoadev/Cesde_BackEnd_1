package app.repository.config;

import app.domain.enums.Ambiente;

public class AppConfig {

    // Variable global de ambiente / persistencia (Por defecto: BD)
    private static Ambiente ambienteActual = Ambiente.BD;

    public static Ambiente getAmbiente() {
        return ambienteActual;
    }

    public static void setAmbiente(Ambiente nuevoAmbiente) {
        if (nuevoAmbiente != null) {
            ambienteActual = nuevoAmbiente;
            System.out.println("\n[CONFIGURACIÓN GLOBAL] El ambiente de persistencia ahora es: " + nuevoAmbiente);
        }
    }
}
