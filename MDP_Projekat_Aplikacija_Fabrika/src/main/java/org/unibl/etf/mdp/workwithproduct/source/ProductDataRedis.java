package org.unibl.etf.mdp.workwithproduct.source;

import java.util.List;

import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithproduct.model.Product;

import redis.clients.jedis.Jedis;

public class ProductDataRedis extends ProductData{
	
	private static Jedis jedis;
	private char SPLIT_SPACE = Property.getProperty().getSPLIT_REDIS_SPACE();
	private char DELIMITER = Property.getProperty().getDELIMITER_REDIS();
	// private JedisPool pool = new JedisPool("localhost");
	
	public ProductDataRedis() {
		jedis = new Jedis(Property.getProperty().getHOST_REDIS(), Property.getProperty().getPORT_REDIS());
		load();
		saveAll();
	}
	
	
	protected void load() {
		products.add(new Product("1111", "Hljeb", 1.2));
		products.add(new Product("1112", "Kifla", 0.4));
		products.add(new Product("1113", "Burek", 3.2));
		products.add(new Product("1114", "Jogurt", 2.8));
		products.add(new Product("1115", "Kefir", 3.2));
		products.add(new Product("1116", "Voda", 1));
	}
	
	private void saveAll() {
		for(Product p : products) {
			try {
				jedis.set(p.getBarcode(), getStringForRedis(p));
			} catch (Exception e) {
			}
		}
	}
	
	@Override
	public boolean add(Product product) {
		try {
			jedis.set(product.getBarcode(), getStringForRedis(product));
			products.add(product);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public Product get(String barcode) {
		String productString = jedis.get(barcode);
		return getProductFromString(productString);
	}
	
	@Override
	public boolean delete(Product product) {
		try {
			System.out.println(product.getBarcode());
			jedis.del(product.getBarcode());
			products.remove(product);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean update(Product product, String barcode) {
		if(barcode == null)
			return false;
		Product p = new Product();
		p.setBarcode(barcode);
		delete(p);
		add(product);
		return true;
	}
	
	private String getStringForRedis(Product product) {
		String name = product.getName().replace(' ', SPLIT_SPACE);
		double price = product.getPrice();
		return product.getBarcode() + DELIMITER + name + DELIMITER + price;
	}
	
	private Product getProductFromString(String productString) {
		Product ret = new Product();
		String[] arr = productString.split("" + DELIMITER);
		ret.setBarcode(arr[0]);
		ret.setName(arr[1].replace(SPLIT_SPACE, ' ' ));
		ret.setPrice(Double.parseDouble(arr[2]));
		return ret;
	}
	
	@Override
	public List<Product> getAll(){
		products.clear();
		for(String key : jedis.keys("*")) {
			products.add(get(key));
		}
		return products;
	}

}
