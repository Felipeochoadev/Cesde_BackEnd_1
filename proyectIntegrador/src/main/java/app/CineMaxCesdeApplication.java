package app;

import app.domain.enums.ModuloSistema;
import app.repository.config.AppConfig;
import app.service.factory.VerticalSliceFactory;
import app.service.helpers.SetPropertyHelper;

public class CineMaxCesdeApplication {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       SISTEMA DE CINE - CINEMAX CESDE");
        System.out.println("       Arquitectura Hexagonal & Dual Factory");
        System.out.println("==================================================");
        System.out.println("Ambiente inicial: " + AppConfig.getAmbiente());

        boolean ejecutando = true;

        while (ejecutando) {
            ModuloSistema modulo = SetPropertyHelper.setModuloSistema();

            if (modulo == ModuloSistema.SALIR) {
                System.out.println("\nGracias por utilizar CineMax CESDE. ¡Hasta pronto!");
                ejecutando = false;
            } else {
                VerticalSliceFactory.ejecutar(modulo);
            }
        }
    }
}
