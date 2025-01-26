package org.unibl.etf.mdp.workwithorders.model;

import java.util.Objects;

public class ProductAndAmount {
	
	private Product product;
	private double amount;
	public ProductAndAmount() {
		super();
	}
	public ProductAndAmount(Product product, double amount) {
		super();
		this.product = product;
		this.amount = amount;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String[] getArray() {
		String[] ret = {product.getBarcode(), product.getName(), product.getPrice() + "", amount + "",  product.getPrice() * amount +""};
		return ret;
	}
	@Override
	public String toString() {
		return String.format("(%s) %s [%.2f]", product.getBarcode(), product.getName(), amount);
	}
	@Override
	public int hashCode() {
		return Objects.hash(product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductAndAmount other = (ProductAndAmount) obj;
		return Objects.equals(product, other.product);
	}
	
	

}
