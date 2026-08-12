package domain;

import java.util.Scanner;

public class Employee extends User{
    public Scanner sc = new Scanner(System.in);

    //Atributes

    private String role;
    private String area;

    @Override
    public void create(){
        super.create();

        System.out.println("Ingrese el role del empleado");
    }

    @Override
    public void select(){

    }
}
