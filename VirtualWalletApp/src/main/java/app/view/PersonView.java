package app.view;

import app.domain.DocumentTypeEnum;
import app.service.inputports.UserUseCase;
import app.utils.FormRuleValidators;

import java.util.Scanner;

public class PersonView {
    Scanner sc = new Scanner(System.in);

    //patron singleton
    private final UserUseCase userUseCase;

    PersonView(UserUseCase userUseCase){
        this.userUseCase = userUseCase;
    }

    public void personView(){

    }

    public void createPerson(){
        int id = FormRuleValidators.validateInt("Ingrese el nombre de la persona: ");
        String name = FormRuleValidators.validateString("Ingrese el nombre de la persona: ");
        String lastname = FormRuleValidators.validateString("Ingrese el apellido de la persona: ");
        String documento = FormRuleValidators.validateDocument("Ingrese el documento donde 1 es cedula, 2 pasaporte, 3 cedula extranjeria, 4 nit ");
        String email = FormRuleValidators.validateString("Ingrese el email de la persona: ");
        String password = FormRuleValidators.validateString("Password: ");
        Boolean estado = FormRuleValidators.validateBoolean("Estado: ");
        String ocupacion = FormRuleValidators.validateString("Ocupacion: ");
        double salario = FormRuleValidators.validateDouble("salario: ");

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