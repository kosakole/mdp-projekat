package org.unibl.etf.mdp.workwithorders.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {
	
	@XmlElement
	private List<ProductAndAmount> products;
	@XmlElement
	private String email;
	public Order() {
		super();
	}
	public Order(List<ProductAndAmount> products, String email) {
		super();
		this.products = products;
		this.email = email;
	}
	public List<ProductAndAmount> getProducts() {
		return products;
	}
	public void setProducts(List<ProductAndAmount> products) {
		this.products = products;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Order [products=" + products + ", email=" + email + "]";
	}
}
