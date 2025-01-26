package org.unibl.etf.mdp.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unibl.etf.mdp.workwithproduct.service.ProductService;

@Path("products")
public class ProductAPI {
	
	private ProductService service;
	
	public ProductAPI() {
		super();
		service = new ProductService();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.status(Response.Status.OK).entity(service.getAll()).build();
	}
}
