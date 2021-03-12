package com.prokopovich.projectmanagement.model;

import java.sql.Blob;

public class Account {
    private int accountId;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
    private String role;
    private Blob photo;

    public Account(){}

    public Account(int accountId, String name, String surname, String patronymic, String email, String password,
                   String role, Blob photo) {
        this.accountId = accountId;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
        this.role = role;
        this.photo = photo;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "\nAccount: " +
                "id = " + accountId +
                ", name = " + name +
                ", surname = " + surname +
                ", patronymic = " + patronymic +
                ", email = " + email +
                ", password = " + password +
                ", role = " + role +
                ", photo = " + photo + ';';
    }
}
