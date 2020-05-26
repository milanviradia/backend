package com.bloodbank.backend.model;
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
    private int age;
    private String bloodGroup;
    private String country;
    private String state;
    private String city;
    private Long zipcode;
    private String passWord;

    public User(){}

    public User(String userName, String emailId, String mobileNumber, int age, String bloodGroup
                ,String country,String state, String city, Long zipcode, String passWord) {
        super();
        this.userName = userName;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.passWord = passWord;
    }

    public long getId() { return id;}
    public void setId(long id) { this.id = id;}

    @Column(name = "name", nullable = false)
    public String getUserName() {return userName;}
    public void setUserName(String userName) {
        System.out.println("Entity Name::"+userName);
        this.userName = userName;
    }

    @Column(name = "email_address", nullable = false)
    public String getEmailId() {return emailId;}
    public void setEmailId(String emailId) {this.emailId = emailId;}

    @Column(name = "mobile_number", nullable = false)
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public int getAge() { return age;}
    public void setAge(int age) { this.age = age;}

    public String getBloodGroup() {return bloodGroup;}
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup;}

    public String getCountry() { return country;}
    public void setCountry(String country) { this.country = country;}

    public String getState() { return state;}
    public void setState(String state) { this.state = state;}

    public String getCity() { return city;}
    public void setCity(String city) { this.city = city;}

    public Long getZipcode() { return zipcode;}
    public void setZipcode(Long zipcode) { this.zipcode = zipcode;}

    @Column(name = "password", nullable = false)
    public String getPassWord() { return passWord; }
    public void setPassWord(String passWord) { this.passWord = passWord; }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", emailId=" + emailId + ", mobileNumber=" + mobileNumber
                + ", age=" + age + ", bloodGroup=" + bloodGroup +", country=" + country + ", state=" + state + ", city="
                + city + ", zipcode=" + zipcode + ", passWord=" + passWord +"]";
    }

}