package org.unibl.etf.mdp.source;

import java.net.URI;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unibl.etf.mdp.dto.UserDTOLogin;
import org.unibl.etf.mdp.workwithusers.model.User;

public class UserSourceREST extends UserSource{

	private final static String PATH;
	private final static String USERS_PATH;
	private final static String ADD_USER_PATH;
	private static Property property = Property.getProperty();
	private static Logger logger;
	
	static {
		PATH = property.getMAIN_SERVER_PATH();
		USERS_PATH = property.getUSERS_API_PATH();
		ADD_USER_PATH = property.getADD_USER_PATH();
		logger = property.getLogger();
	}
	@Override
	public boolean chackUser(UserDTOLogin user) {
		Client client = ClientBuilder.newClient();
		try {
			URI base = new URI(PATH);
			WebTarget target = client.target(base).path(USERS_PATH);
			Response response  = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(user, MediaType.APPLICATION_JSON));
			if(response.getStatusInfo().toEnum() == Response.Status.OK) {
				return true;
			}
		} catch (Exception e) {
			logger.severe(e.toString());
		}
		return false;
	}
	
	@Override
	public boolean add(User user) {
		Client client = ClientBuilder.newClient();
		try {
			URI base = new URI(PATH);
			WebTarget target = client.target(base).path(ADD_USER_PATH);
			Response response  = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(user, MediaType.APPLICATION_JSON));
			if(response.getStatusInfo().toEnum() == Response.Status.OK) {
				return true;
			}
		} catch (Exception e) {
			logger.severe(e.toString());
		}
		return false;
	}
}
