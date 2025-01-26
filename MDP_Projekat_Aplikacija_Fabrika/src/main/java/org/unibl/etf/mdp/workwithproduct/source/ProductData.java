package org.unibl.etf.mdp.workwithproduct.source;

import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.mdp.workwithproduct.model.Product;

public class ProductData {
	
	protected List<Product> products;
	private static ProductData instance = null;
	

	protected ProductData() {
		products = new ArrayList<Product>();
		load();
	}
	
	public static ProductData getInstance() {
		if(instance == null)
			instance = new ProductData();
		return instance;
	}
	
	public static ProductData getInstanceRedis() {
		if(instance == null)
			instance = new ProductDataRedis();
		return instance;
	}


	protected void load() {
		
	}
	
	public boolean add(Product product) {
		return false;
	}
	
	public Product get(String barcode) {
		return null;
	}
	
	public List<Product> getAll(){
		return products;
	}
	
	public boolean delete(Product product) {
		return false;
	}
	
	public boolean update(Product product, String barcode) {
		return false;
	}
}
