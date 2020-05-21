package com.bloodbank.backend.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    
	private long zipcode;
	private String Society;
	
	@ManyToOne
	private User user = new User();
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
        return id;
    }
	
    public void setId(long id) {
        this.id = id;
    }
	public long getZipcode() {
		return zipcode;
	}
	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}
	public String getSociety() {
		return Society;
	}
	public void setSociety(String society) {
		Society = society;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	private String city;
	@Override
	public String toString() {
		return "address [zipcode=" + zipcode + ", Society=" + Society + ", city=" + city + "]";
	}
	
}