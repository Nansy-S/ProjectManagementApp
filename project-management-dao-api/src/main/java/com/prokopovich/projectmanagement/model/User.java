package com.prokopovich.projectmanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "position")
    private String position;
    @Column(name = "current_status")
    private String currentStatus;
    @Column(name = "phone")
    private String phone;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account accountInfo;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private List<AccountAction> accountActions;

    public User() {
        accountInfo = new Account();
        accountActions = new ArrayList<>();
    }

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
}
