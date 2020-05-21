package com.bloodbank.backend.model;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private String userName;
    private String emailId;
    private String mobileNumber;
    private String passWord;
    
    @OneToMany(targetEntity = Address.class,cascade=CascadeType.ALL)
    @JoinColumn(name="user_fk",referencedColumnName= "id")
    private List<Address> address;
    public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public User() {
    }

    public User(String userName, String emailId, String mobileNumber, String passWord)
    {
        this.userName = userName;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.passWord = passWord;
    }
   
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        System.out.println("Entity Name::"+userName);
        this.userName = userName;
    }

    @Column(name = "email_address", nullable = false)
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name = "mobile_number", nullable = false)
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    @Column(name = "password", nullable = false)
    public String getPassWord() { return passWord; }
    public void setPassWord(String passWord) { this.passWord = passWord; }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", emailId=" + emailId
        + ", mobileNumber=" + mobileNumber + ", passWord=" + passWord  + "]";
    }
}