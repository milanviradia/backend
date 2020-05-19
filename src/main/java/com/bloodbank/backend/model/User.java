package com.bloodbank.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    private long id;
    private String Name;
    private String emailId;
    private long mobileNumber;
    private  String passWord;

    public User() {

    }

    public User(String Name, String emailId, long mobileNumber, String passWord)
    {
        this.Name = Name;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.passWord = passWord;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    @Column(name = "email_address", nullable = false)
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name = "mobile_number", nullable = false)
    public long getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(long mobileNumber) { this.mobileNumber = mobileNumber; }

    @Column(name = "password", nullable = false)
    public String getPassWord() { return passWord; }
    public void setPassWord(String passWord) { this.passWord = passWord; }

    @Override
    public String toString() {
        return "User [id=" + id + ", Name=" + Name + ", emailId=" + emailId
        + "mobileNumber=" + mobileNumber + "passWord=" + passWord  + "]";
    }
}