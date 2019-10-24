package model;

public class Contact {

    private String email;
    private String name;
    private String surname;

    public Contact(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email: "+this.email+"\n");
        sb.append("Name: "+this.name+"\n");
        sb.append("Surname: "+this.surname+"\n");
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
