package org.unibl.etf.mdp.workwithusers.service;

import java.util.List;

import org.unibl.etf.mdp.workwithusers.model.User;
import org.unibl.etf.mdp.workwithusers.model.UserDTOLogin;
import org.unibl.etf.mdp.workwithusers.source.UserDataJSON;
import org.unibl.etf.mdp.workwithusers.source.UsersData;

public class UserService {

	private UsersData data = new UserDataJSON();

	public UserService() {
		super();
	}

	public UserService(UsersData data) {
		super();
		this.data = data;
	}
	
	public List<User> getAll(){
		return data.getAll();
	}
	
	public boolean update(User user) {
		return data.updateUser(user);
	}
	
	public boolean delete(User user) {
		return data.deleteUser(user);
	}
	
	public boolean checkUser(UserDTOLogin user) {
		return data.checkUser(user);
	}
	
	public boolean add(User user) {
		return data.addUser(user);
	}
}
