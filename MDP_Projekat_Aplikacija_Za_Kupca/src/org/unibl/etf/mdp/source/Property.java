package org.unibl.etf.mdp.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Property {

	private static final String PROPERT_FILE = "conf.property";
	private static Property instance = null;
	private String LOGGER_FILE;
	private Logger logger;
	private String LOGGER_NAME;
	private String MULTICAST_HOST;
	private int MULTICAST_PORT;
	private String SERVER_HOST;
	private int SERVER_PORT;
	private String MAIN_SERVER_PATH;
	private String USERS_API_PATH;
	private String PRODUCT_API_PATH;
	private String ADD_USER_PATH;

	private Property() {
		Properties property = new Properties();
		try {
			property.load(
					new FileInputStream(new File(PROPERT_FILE)));
			LOGGER_FILE = property.getProperty("LOGGER_FILE");
			LOGGER_NAME = property.getProperty("LOGGER_NAME");
			MULTICAST_HOST = property.getProperty("MULTICAST_HOST");
			MULTICAST_PORT = Integer.parseInt(property.getProperty("MULTICAST_PORT"));
			SERVER_HOST = property.getProperty("SERVER_HOST");
			SERVER_PORT = Integer.parseInt(property.getProperty("SERVER_PORT"));
			MAIN_SERVER_PATH = property.getProperty("MAIN_SERVER_PATH");
			USERS_API_PATH = property.getProperty("USERS_API_PATH");
			PRODUCT_API_PATH = property.getProperty("PRODUCT_API_PATH");
			ADD_USER_PATH = property.getProperty("ADD_USER_PATH");
			initLogger();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initLogger() {
		logger = Logger.getLogger(LOGGER_NAME);
		File f = new File(LOGGER_FILE);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileHandler hander = new FileHandler(LOGGER_FILE, true);
			logger.addHandler(hander);
			hander.setFormatter(new SimpleFormatter());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Logger getLogger() {
		return logger;
	}

	public String getMULTICAST_HOST() {
		return MULTICAST_HOST;
	}

	public int getMULTICAST_PORT() {
		return MULTICAST_PORT;
	}

	public String getSERVER_HOST() {
		return SERVER_HOST;
	}

	public int getSERVER_PORT() {
		return SERVER_PORT;
	}

	public String getMAIN_SERVER_PATH() {
		return MAIN_SERVER_PATH;
	}

	public String getUSERS_API_PATH() {
		return USERS_API_PATH;
	}

	public String getPRODUCT_API_PATH() {
		return PRODUCT_API_PATH;
	}
	
	

	public String getADD_USER_PATH() {
		return ADD_USER_PATH;
	}

	public static Property getProperty() {
		if (instance == null)
			instance = new Property();
		return instance;
	}
}
