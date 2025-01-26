package org.unibl.etf.mdp.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.unibl.etf.mdp.model.UserFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserFactoryDataJSON extends UserFactoryData{

	private List<UserFactory> users = new ArrayList<>();
	private static Logger logger;
	
	static {
		logger = Property.getProperty().getLogger();
	}
	
	{
		users.add(new UserFactory("kole"));
		users.add(new UserFactory("vasa"));
		users.add(new UserFactory("tripa"));
		users.add(new UserFactory("hana"));
	}
	@Override
	public boolean contain(UserFactory user) {
		List<UserFactory> list = getAll();
		if(list.contains(user))
			return true;
		return false;
		
	}

	private List<UserFactory> getAll() {
		List<UserFactory> ret = new ArrayList<>();
		Scanner scan = null;
		Gson gson = new Gson();
		Type type = new TypeToken<List<UserFactory>>(){}.getType();
		File file = new File(Property.getProperty().getFILE_USERS_FACTORY());
		String list = "";
		
		try {
			scan = new Scanner(file);
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
}
