package com.noteit.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "emailAddress"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(nullable = false, unique = true)
    private String emailAddress;
    @Column(nullable = false)
    private int userType;
    @Column(nullable = false)
    private char accountStatus;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "uploadedBy")
    private List<Book> uploadedBooks;

    @OneToMany(mappedBy = "userId")
    private List<Notebook> notebooks;


    public User() {
    }

    public User(String emailAddress, int userType, char accountStatus, String password, String name, String phoneNumber) {
        this.emailAddress = emailAddress;
        this.userType = userType;
        this.accountStatus = accountStatus;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public char getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(char accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}