package com.twu.biblioteca.model;

public class User {
    private Integer id;
    private String name;
    private String number;
    private String password;
    private String status;

    public User(Integer id, String name, String number, String password, String status) {
        this.id = id;
        this.name = name;
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

    public void setStatus(String status) {
        this.status = status;
    }

}
