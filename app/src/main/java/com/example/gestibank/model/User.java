package com.example.gestibank.model;

public class User {
    private String name;
    private String firstname;
    private String phone;
    private String mail;
    private String password;
    private String role;
    private String activated;

    public User(){}

    public User(String name, String firstname, String phone, String mail, String password, String role, String activated) {
        this.name = name;
        this.firstname = firstname;
        this.phone = phone;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.activated = activated;
    }
    public User(String name, String firstname, String phone, String mail, String role, String activated) {
        this.name = name;
        this.firstname = firstname;
        this.phone = phone;
        this.mail = mail;
        this.role = role;
        this.activated = activated;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", activated='" + activated + '\'' +
                '}';
    }
}
