package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Lob
    @Column(name = "photo")
    private Blob photo;

    public Account() { }

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
        return "Account: " +
                "id = " + accountId +
                ", name = " + name +
                ", surname = " + surname +
                ", patronymic = " + patronymic +
                ", email = " + email +
                ", role = " + role +
                ", photo = " + photo + ";";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId &&
                Objects.equals(name, account.name) &&
                Objects.equals(surname, account.surname) &&
                Objects.equals(patronymic, account.patronymic) &&
                Objects.equals(email, account.email) &&
                Objects.equals(password, account.password) &&
                Objects.equals(role, account.role) &&
                Objects.equals(photo, account.photo);
    }

    @Override
    public int hashCode() {
        int result = accountId;
        result = 37 * result + (name != null ? name.hashCode() : 0);
        result = 37 * result + (surname != null ? surname.hashCode() : 0);
        result = 37 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 37 * result + (email != null ? email.hashCode() : 0);
        result = 37 * result + (password != null ? password.hashCode() : 0);
        result = 37 * result + (role != null ? role.hashCode() : 0);
        result = 37 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }
}
