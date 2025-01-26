package org.unibl.etf.mdp.workwithusers.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithusers.model.User;
import org.unibl.etf.mdp.workwithusers.model.UserDTOLogin;
import org.unibl.etf.mdp.workwithusers.model.UserStatus;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserDataJSON extends UsersData {

	private static Property property;
	private String FILE_USERS;
	private List<User> users = new ArrayList<>();
	private static Logger logger;
	
	static {
		property = Property.getProperty();
		logger = property.getLogger();
	}

	public UserDataJSON() {
		FILE_USERS = property.getFileUsers();
	}

	@Override
	public List<User> getAll() {
		List<User> ret = new ArrayList<>();
		Scanner scan = null;
		Gson gson = new Gson();
		Type type = new TypeToken<List<User>>(){}.getType();
		File file = new File(FILE_USERS);
		String list = "";
		try {
			scan = new Scanner(new FileInputStream(file));
			while (scan.hasNext()) {
				list += scan.nextLine();
			}
			ret = gson.fromJson(list, type);
		} catch (FileNotFoundException e) {
			logger.severe(e.getMessage());
		} finally {
			if (scan != null)
				scan.close();
		}

		return ret;
	}

	@Override
	public boolean addUser(User user) {
		users = getAll();
		if(users.contains(user)){
			return false;
		}
		users.add(user);
		saveAll();
		return true;
	}
	
	@Override 
	public boolean updateUser(User user) {
		if(!deleteUser(user))
			return false;
		addUser(user);
		return true;
	}
	
	@Override
	public boolean deleteUser(User user) {
		users = getAll();
		if(!users.contains(user))
			return false;
		users.remove(user);
		saveAll();
		return true;
	}
	
	private void saveAll() {
		Gson gson = new Gson();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter((new FileOutputStream(FILE_USERS, false)), true);
			pw.println(gson.toJson(users));	
		} catch (FileNotFoundException e) {
			logger.severe(e.getMessage());
		}finally {
			if (pw != null)
				pw.close();
		}
		users.clear();
	}
	
	@Override
	public boolean checkUser(UserDTOLogin user) {
		List<User> list = getAll();
		for(User u : list) {
			if(u.getUsername().equals(user.getUsername())) {
				if(u.getPassword().equals(user.getPassword()) && u.getStatus() == UserStatus.APPROVED)
					return true;
				return false;
			}
		}
		return false;
	}
}
