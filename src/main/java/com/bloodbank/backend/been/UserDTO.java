package com.bloodbank.backend.been;

import java.io.Serializable;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String mobileNumber;
	private String bloodGroup;
	
	public UserDTO() {}
	public UserDTO(String name, String mobileNumber, String bloodGroup) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.bloodGroup = bloodGroup;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	@Override
	public String toString() {
		return "userDTO [name=" + name + ", mobileNumber=" + mobileNumber + ", bloodGroup=" + bloodGroup + "]";
	}
	
	
	
}
