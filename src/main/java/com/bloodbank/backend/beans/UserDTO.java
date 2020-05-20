package com.bloodbank.backend.beans;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    long id;
    private String userName;
    private String emailId;
    private String mobileNumber;
    private String passWord;
    private String role;

    public UserDTO() {
    }

    public UserDTO(String userName, String emailId, String mobileNumber, String passWord) {
        this.userName = userName;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.passWord = passWord;

        System.out.println("UserDTO userName::" + this.userName);
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        System.out.println("Name::" + userName);
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
        System.out.println("Email::" + emailId);
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}