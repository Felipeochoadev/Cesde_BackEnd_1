package app.view;

import app.domain.DocumentTypeEnum;
import app.service.helpers.SetDocumentType;
import app.service.inputports.UserUseCase;

import java.util.Scanner;

public class PersonView {
    Scanner sc = new Scanner(System.in);

    private final UserUseCase userUseCase;

    PersonView(UserUseCase userUseCase){
        this.userUseCase = userUseCase;
    }

    public void personView(){

    }

    public void createPerson(){
        System.out.println("Creating Person");

        System.out.println("ingrese el id del cliente:");

        Integer id = sc.nextInt();
        sc.nextLine();

        System.out.println("ingrese el nombre del cliente:");
        String name = sc.nextLine();

        System.out.println("ingrese el apellido del cliente:");
        String lastname = sc.nextLine();

        System.out.println("ingrese el tipo del documento:");
        String documento = SetDocumentType.getDocumentType();

        System.out.println("ingrese el email del cliente:");
        String email = sc.nextLine();

        System.out.println("ingrese el password del cliente:");
        String password = sc.nextLine();

        System.out.println("ingrese el estado del cliente:");
        Boolean estado = sc.nextBoolean();

        System.out.println("ingrese la ocupacion del cliente:");
        String ocupacion = sc.nextLine();

        System.out.println("ingrese el salario del cliente:");
        double salario = sc.nextDouble();

        userUseCase.createPerson(id, name, lastname, DocumentTypeEnum.valueOf(documento), email, password, estado, ocupacion, salario);
    }

    public void showPerson(){
        System.out.println("Person View");
    }

    public void ShowPersonById(){
        System.out.println("Getting person by id");
    }

    public void updatePerson(){
        System.out.println("Updating person");
    }

    public void deletePerson(){
        System.out.println("Deleting person");
    }

}