package org.unibl.etf.mdp.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unibl.etf.mdp.workwithusers.model.User;
import org.unibl.etf.mdp.workwithusers.model.UserDTOLogin;
import org.unibl.etf.mdp.workwithusers.service.UserService;

@Path("users")
public class UserAPI {

	private final static String ADD_PATH = "add";
	private UserService service = new UserService();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkUser(UserDTOLogin user) {
		if(service.checkUser(user))
			return Response.status(Response.Status.OK).build();
		return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ADD_PATH)
	public Response createUser(User user) {
		if(service.add(user))
			return Response.status(Response.Status.OK).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
}
