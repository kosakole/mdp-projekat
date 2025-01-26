package org.unibl.etf.mdp.workwithproduct.model;

import java.util.Objects;

public class Product {

	private String barcode;
	private String name;
	private double price;
	public Product() {
		super();
	}
	public Product(String barcode, String name, double price) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.price = price;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		return Objects.hash(barcode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(barcode, other.barcode);
	}
	@Override
	public String toString() {
		return "Product: " + name ;
	}
	
	public String[] getProductStringArray() {
		String[] ret = {barcode, name, price+""}; 
		return ret;
	}
}
