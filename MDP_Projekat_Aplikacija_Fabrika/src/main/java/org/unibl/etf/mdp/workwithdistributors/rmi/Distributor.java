package org.unibl.etf.mdp.workwithdistributors.rmi;

import java.io.Serializable;
import java.util.Objects;

public class Distributor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public Distributor() {
		super();
	}

	
	public Distributor(String name) {
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
		return name;
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
		Distributor other = (Distributor) obj;
		return Objects.equals(name, other.name);
	}
	
	
}
