package app.domain;

public class User {
    private Integer id;
    private String Name;
    private String LastName;
    private DocumentTypeEnum documentType;
    private String email;
    private String password;
    private boolean state;

    public User(){

    }

    public User(Integer id, String name, String lastName, DocumentTypeEnum documentType, String email, String password, boolean state){
        this.id = id;
        this.Name = name;
        this.LastName = lastName;
        this.documentType = documentType;
        this.email = email;
        this.password = password;
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", documentType=" + documentType +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                '}';
    }
}
