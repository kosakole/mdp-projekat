package org.unibl.etf.mdp.dto;

import java.util.Objects;

public class UserDTOLogin {

	private String username;
	private String password;
	public UserDTOLogin() {
		super();
	}
	public UserDTOLogin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTOLogin other = (UserDTOLogin) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
}
