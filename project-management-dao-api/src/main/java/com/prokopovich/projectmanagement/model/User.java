package com.prokopovich.projectmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "position")
    private String position;
    @Column(name = "current_status")
    private String currentStatus;
    @Column(name = "phone")
    private String phone;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Account accountInfo;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<AccountAction> accountActions;

    public User() { }

    public User(int userId, String position, String currentStatus, String phone, Account accountInfo) {
        this.userId = userId;
        this.position = position;
        this.currentStatus = currentStatus;
        this.phone = phone;
        this.accountInfo = accountInfo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Account getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Account accountInfo) {
        this.accountInfo = accountInfo;
    }

    public List<AccountAction> getAccountActions() {
        return accountActions;
    }

    public void setAccountActions(List<AccountAction> accountActions) {
        this.accountActions = accountActions;
    }

    @Override
    public String toString() {
        return "User: " +
                "userId = " + userId +
                ", position = " + position +
                ", currentStatus = " + currentStatus +
                ", phone = " + phone +
                "; " + accountInfo.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(position, user.position) &&
                Objects.equals(currentStatus, user.currentStatus) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 37 * result + (position != null ? position.hashCode() : 0);
        result = 37 * result + (currentStatus != null ? currentStatus.hashCode() : 0);
        result = 37 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
