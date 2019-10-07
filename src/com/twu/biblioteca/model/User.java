package com.twu.biblioteca.model;

public class User {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private String number;
    private String password;
    private String status;

    public User(Integer id, String name, String email, String phoneNumber, String number, String password, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.number = number;
        this.password = password;
        this.status = status;
    }

    public Integer getId() { return  this.id; }

    public String getNumber() {
        return this.number;
    }

    public String getPassword() {
        return this.password;
    }

    public String getStatus() { return this.status; }

    public String getName() { return  this.name; }

    public String getEmail() { return this.email; }

    public String getPhoneNumber() { return this.phoneNumber; }

    public void setStatus(String status) {
        this.status = status;
    }

}
