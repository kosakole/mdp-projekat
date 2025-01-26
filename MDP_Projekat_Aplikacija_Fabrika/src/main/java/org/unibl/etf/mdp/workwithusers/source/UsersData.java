package org.unibl.etf.mdp.workwithusers.source;

import java.util.List;

import org.unibl.etf.mdp.workwithusers.model.User;
import org.unibl.etf.mdp.workwithusers.model.UserDTOLogin;

public class UsersData {

	public UsersData() {

	}

	public List<User> getAll() {
		return null;
	}

	protected List<User> loadUsers() {
		return null;
	}

	public boolean addUser(User user) {
		return false;
	}

	public boolean updateUser(User user) {
		return false;
	}

	public boolean deleteUser(User user) {
		return false;
	}

	public boolean checkUser(UserDTOLogin user) {
		return false;
	}

}
