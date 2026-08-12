package main;

import domain.Employee;
import domain.Admin;


public class App {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.create();
        employee.select();


        System.out.println("Ahora con el admin");
        Admin admin  = new Admin();
        admin.create();
        admin.select();
    }

}
