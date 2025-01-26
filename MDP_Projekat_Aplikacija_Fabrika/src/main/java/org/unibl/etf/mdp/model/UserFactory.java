package org.unibl.etf.mdp.model;

import java.util.Objects;

public class UserFactory {

	private String name;

	public UserFactory() {
		super();
	}

	public UserFactory(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserFactory [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFactory other = (UserFactory) obj;
		return Objects.equals(name, other.name);
	}
	

}
