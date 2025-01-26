package org.unibl.etf.mdp.source;

import org.unibl.etf.mdp.dto.UserDTOLogin;
import org.unibl.etf.mdp.workwithusers.model.User;

public class UserSource {

	private UserSource instance;
	
	protected UserSource() {
		
	}
	
	public UserSource getInstace() {
		if(instance == null)
			instance = new UserSource();
		return instance;
	}
	
	public UserSource getInstaceREST() {
		if(instance == null)
			instance = new UserSource();
		return instance;
	}
	
	public boolean chackUser(UserDTOLogin user) {
		return false;
	}
	
	public boolean add(User user) {
		return false;
	}
}
