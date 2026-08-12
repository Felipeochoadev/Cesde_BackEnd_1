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

        System.out.println("Ingrese el rol del usuario");
        this.role = sc.nextLine();

        System.out.println("Ingrese el area del usuario");
        this.area = sc.nextLine();

    }

    @Override
    public void select(){
        super.select();

        System.out.println("Rol: " +this.role + "\n"
                + "Area: " +this.area + "\n"
        );
    }

    @Override
    public void selectById(int id){
        super.selectById(id);
    }

    @Override
    public void update(){
        super.update();
    }
}
