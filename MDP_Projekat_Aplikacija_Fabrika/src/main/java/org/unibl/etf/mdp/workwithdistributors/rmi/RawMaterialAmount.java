package org.unibl.etf.mdp.workwithdistributors.rmi;

import java.io.Serializable;
import java.util.Objects;

public class RawMaterialAmount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RawMaterial rawMaterial;
	private double amount;
	public RawMaterialAmount() {
		super();
	}
	public RawMaterialAmount(RawMaterial rawMaterial, double amount) {
		super();
		this.rawMaterial = rawMaterial;
		this.amount = amount;
	}
	public RawMaterial getRawMaterial() {
		return rawMaterial;
	}
	public void setRawMaterial(RawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return rawMaterial + " #" + amount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(rawMaterial);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RawMaterialAmount other = (RawMaterialAmount) obj;
		return Objects.equals(rawMaterial, other.rawMaterial);
	}
	
	

}
