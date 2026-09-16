package app.utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import app.service.helpers.SetDocumentType;

public class FormRuleValidators {

    static Scanner sc = new Scanner(System.in);

    public static int validateInt(String prompt){
        while(true){
            try{
                System.out.println(prompt);
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } catch(InputMismatchException e){
                System.out.println("Error: Ingrese un numero Entero" + e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static Double validateDouble(String prompt){
        while(true){
            try{
                System.out.println(prompt);
                Double value = sc.nextDouble();
                sc.nextLine();
                return value;
            } catch(InputMismatchException e){
                System.out.println("Error: Ingrese un numero decimal valido" + e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static Boolean validateBoolean(String prompt){
        while(true){
            try{
                System.out.println(prompt);
                Boolean value = sc.nextBoolean();
                sc.nextLine();
                return value;
            } catch(InputMismatchException e){
                System.out.println("Error: Ingrese un valor valido" + e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static String validateString(String prompt){
        while(true){
            try{
                System.out.println(prompt);
                String value = sc.nextLine().trim();
                if(!value.isEmpty()){
                    return value;
                }
            } catch(InputMismatchException e){
                System.out.println("ingrese un valor de texto valido" + e.getMessage());
            }
        }
    }

    public static String validateDocument(String prompt){
        while(true){
            try{
                System.out.println(prompt);
                int value = sc.nextInt();
                sc.nextLine();
                return SetDocumentType.getDocumentType(value);
            } catch(InputMismatchException e){
                System.out.println("ingrese un valor de texto valido" + e.getMessage());
            }
        }
    }



}
