package app.domain;

public class Person extends User {

    private String ocuppation;
    private String salary;

    public Person() {
        super();
    }

    public Person(Integer id, String name, String lastName, DocumentTypeEnum documentType, String email, String password, boolean state, String ocuppation, String salary) {
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
