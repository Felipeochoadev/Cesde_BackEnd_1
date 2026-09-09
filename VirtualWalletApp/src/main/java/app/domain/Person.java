package app.domain;

public class Person extends User {

    private String ocuppation;
    private double salary;

    public Person() {
        super();
    }

    public Person(Integer id, String name, String lastName, DocumentTypeEnum documentType, String email, String password, boolean state, String ocuppation, double salary) {
        super(id, name, lastName, documentType, email, password, state);
        this.ocuppation = ocuppation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ocuppation='" + ocuppation + '\'' +
                ", salary='" + salary + '\'' +
                "} " + super.toString();
    }
}
