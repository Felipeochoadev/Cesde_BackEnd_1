package app.service.validations;

import java.util.Scanner;

public class FormTypeValidator {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }

    public static int readIntOptional(String message, int defaultValue) {
        while (true) {
            System.out.print(message + " [Actual: " + defaultValue + "] (Enter para mantener): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return defaultValue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }

    public static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim().replace(',', '.');
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numérico válido (ej: 15000 o 12.5).");
            }
        }
    }

    public static double readDoubleOptional(String message, double defaultValue) {
        while (true) {
            System.out.print(message + " [Actual: " + defaultValue + "] (Enter para mantener): ");
            String input = scanner.nextLine().trim().replace(',', '.');
            if (input.isEmpty()) {
                return defaultValue;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numérico válido.");
            }
        }
    }

    public static String readString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: El campo no puede estar vacío. Intente de nuevo.");
        }
    }

    public static String readStringOptional(String message, String defaultValue) {
        System.out.print(message + " [Actual: " + defaultValue + "] (Enter para mantener): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return defaultValue;
        }
        return input;
    }

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
            System.out.println("Error: Ingrese 's' para Sí o 'n' para No.");
        }
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
