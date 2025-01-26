package org.unibl.etf.mdp.service;

import org.unibl.etf.mdp.dto.UserDTOLogin;
import org.unibl.etf.mdp.source.UserSource;
import org.unibl.etf.mdp.source.UserSourceREST;
import org.unibl.etf.mdp.workwithusers.model.User;

public class UserService {

	private UserSource data = new UserSourceREST();
	
	public boolean checkUser(UserDTOLogin user) {
		return data.chackUser(user);
	}
	
	public boolean add(User user) {
		return data.add(user);
	}
}
