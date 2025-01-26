package org.unibl.etf.mdp.workwithusers.model;

import java.util.Objects;

public class User {

	private String companyName;
	private String address;
	private String phone;
	private String username;
	private String password;
	private UserStatus status;

	public User() {
		super();
		status = null;
	}

	public User(String companyName, String address, String phone, String username, String password) {
		super();
		this.companyName = companyName;
		this.address = address;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}
	
	

	public User(String companyName, String address, String phone, String username, String password, UserStatus status) {
		super();
		this.companyName = companyName;
		this.address = address;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [companyName=" + companyName + ", address=" + address + ", phone=" + phone + ", username="
				+ username + ", status=" + status + "]";
	}

	public String[] getUserStringArray() {
		String[] ret = {companyName, address, phone, username, status == null ? "" : status.toString()}; 
		return ret;
	}
}
