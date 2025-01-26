package org.unibl.etf.mdp.workwithproduct.service;

import java.util.List;

import org.unibl.etf.mdp.workwithproduct.model.Product;
import org.unibl.etf.mdp.workwithproduct.source.ProductData;

public class ProductService {

	private ProductData data;
	
	public ProductService() {
		data = ProductData.getInstanceRedis();
	}
	
	public boolean create(Product product) {
		return data.add(product);
	}
	
	public boolean update(Product product, String barcode) {
		return data.update(product, barcode);
	}
	
	public boolean delete(Product product) {
		return data.delete(product);
	}
	
	public List<Product> getAll(){
		return data.getAll();
	}
}
