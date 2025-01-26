package org.unibl.etf.mdp.source;

import java.util.List;

import org.unibl.etf.mdp.workwithorders.model.*;

public class ProductData {
	
	private static ProductData instance = null;
	

	protected ProductData() {
		load();
	}
	
	public static ProductData getInstance() {
		if(instance == null)
			instance = new ProductData();
		return instance;
	}
	
	public static ProductData getInstanceREST() {
		if(instance == null)
			instance = new ProductDataREST();
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
		return instance.getAll();
	}
	
	public boolean delete(Product product) {
		return false;
	}
	
	public boolean update(Product product, String barcode) {
		return false;
	}
}
