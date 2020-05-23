package com.bloodbank.backend.been;

import java.util.List;

public class AddressDTO {
	private List<UserDTO> users;
	private String address;
	
	public AddressDTO() {}
	
	public AddressDTO(List<UserDTO> users, String address) {
		super();
		this.users = users;
		this.address = address;
	}
	
	
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "addressDTO [users=" + users + ", address=" + address + "]";
	}
}
