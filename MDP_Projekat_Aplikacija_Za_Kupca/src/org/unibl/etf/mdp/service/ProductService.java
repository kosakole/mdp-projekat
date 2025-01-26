package org.unibl.etf.mdp.service;

import java.util.List;

import org.unibl.etf.mdp.workwithorders.model.*;
import org.unibl.etf.mdp.source.ProductData;

public class ProductService {

	private ProductData dataInstace = ProductData.getInstanceREST();
	
	public ProductService() {
		
	}
	
	public List<Product> getAll(){
		return dataInstace.getAll();
	}
}
