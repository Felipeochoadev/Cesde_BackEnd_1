package domain;

import java.util.Scanner;

public class User {
    public Scanner sc = new Scanner(System.in);

    //Atributes

    private int id;
    private String name;
    private String lasName;
    private String email;
    private String phone;
    private boolean status;

    //Methods

    public void create(){
        System.out.println("Ingrese el id del usuario");
        this.id = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el nombre del usuario");
        this.name = sc.nextLine();

        System.out.println("Ingrese el apellido del usuario");
        this.lasName = sc.nextLine();

        System.out.println("Ingrese el correo del usuario");
        this.email = sc.nextLine();

        System.out.println("Ingrese el telefono del usuario");
        this.phone = sc.nextLine();

        System.out.println("Ingrese el estado del usuario, 1 para activo o 0 para inactivo");
        int state = sc.nextInt();
        this.status = setStatus(state);

    }

     public void select(){
         System.out.println("hola, eres el usuario " + "\n"
                 + "ID: " +this.id + "\n"
                 + "Nombre: " +this.name + "\n"
                 + "Apellido: " +this.lasName + "\n"
                 + "Correo: " +this.email + "\n"
                 + "Telefono: " +this.phone + "\n"
                 + "Estado: " +this.status + "\n"
         );

     }

    public void selectById(int id){

    }

    public void update(){

    }

    public void delete(){

    }

    //Helper
    protected boolean setStatus(int state){
        if (state == 1){
            return true;
        } else {
            return false;
        }
    }

}
