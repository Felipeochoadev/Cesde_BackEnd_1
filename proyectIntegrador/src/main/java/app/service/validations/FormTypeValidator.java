package app.service.validations;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FormTypeValidator {

    // Única instancia de Scanner para toda la aplicación
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Valida y captura un número entero evitando InputMismatchException y errores de formato.
     */
    public static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número entero válido.");
            }
        }
    }

    /**
     * Valida y captura un número decimal (double) evitando caídas por texto o formato.
     */
    public static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim().replace(',', '.');
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un valor numérico válido (ej: 15000 o 12.5).");
            }
        }
    }

    /**
     * Valida y captura una cadena de texto asegurando que no esté vacía.
     */
    public static String readString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("❌ Error: El campo no puede estar vacío. Intente de nuevo.");
        }
    }

    /**
     * Valida y captura una opción booleana (si / no).
     */
    public static boolean readBoolean(String message) {
        while (true) {
            System.out.print(message + " (s/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("s") || input.equals("si") || input.equals("true") || input.equals("1")) {
                return true;
            }
            if (input.equals("n") || input.equals("no") || input.equals("false") || input.equals("0")) {
                return false;
            }
            System.out.println("❌ Error: Ingrese 's' para Sí o 'n' para No.");
        }
    }

    /**
     * Permite obtener acceso al Scanner centralizado si se requiere.
     */
    public static Scanner getScanner() {
        return scanner;
    }
}
