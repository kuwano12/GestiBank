package com.example.gestibank.model;

public class Client {
    public String name;
    public String firstname;
    public String phoneNumb;
    public String email;

    public Client(){}
    public Client(String name, String firstname, String phoneNumb, String email) {
        this.name = name;
        this.firstname = firstname;
        this.phoneNumb = phoneNumb;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(String phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", phoneNumb='" + phoneNumb + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
