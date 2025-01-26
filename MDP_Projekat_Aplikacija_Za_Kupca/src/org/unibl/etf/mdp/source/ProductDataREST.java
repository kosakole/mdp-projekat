package org.unibl.etf.mdp.source;


import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unibl.etf.mdp.workwithorders.model.*;

public class ProductDataREST extends ProductData{
	
	private final static String PATH;
	private final static String PRODUCTS_PATH;
	private static Property property = Property.getProperty();
	private static Logger logger;
	
	static {
		PATH = property.getMAIN_SERVER_PATH();
		PRODUCTS_PATH = property.getPRODUCT_API_PATH();
		logger = property.getLogger();
	}
	@Override
	public List<Product> getAll(){
		Client client = ClientBuilder.newClient();
		try {
			URI base = new URI(PATH);
			WebTarget target = client.target(base).path(PRODUCTS_PATH);
			Response response  = target.request(MediaType.APPLICATION_JSON).get();
			if(response.getStatusInfo() == Response.Status.OK) {
				return response.readEntity(new GenericType<List<Product>>() {});
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		return null;
	}
}
