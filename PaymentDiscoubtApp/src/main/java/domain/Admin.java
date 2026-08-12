package domain;

import java.util.Scanner;

public class Admin extends User{
    public Scanner sc = new Scanner(System.in);

    //Atributes

    private boolean isAdmin;

    @Override
    public void create(){
        super.create();

        System.out.println("Ingrese el tipo del usuario, 1 si es super o 0 para si no lo es");
        int type = sc.nextInt();
        this.isAdmin = setStatus(type);
    }

    @Override
    public void select(){
        super.select();

        System.out.println("Es Admin: " +this.isAdmin
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

    @Override
    public void delete(){
        super.delete();
    }

    @Override
    protected boolean setStatus(int type){
        return super.setStatus(type);
    }
}
