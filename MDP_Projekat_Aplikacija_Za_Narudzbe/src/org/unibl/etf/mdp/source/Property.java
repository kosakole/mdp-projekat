package org.unibl.etf.mdp.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Property {

	public static final String PROPERT_FILE = "conf.property";
	public static final String FILE_USERS = "FILE_USERS";
	public static Property instance = null;
	private String LOGGER_FILE;
	private Logger logger;
	private String LOGGER_NAME;
	private String SERVER_HOST;
	private int SERVER_PORT;
	private String KEY_STORE_PATH;
	private String KEY_STORE_PASSWORD;
	private String MAIL_PROPERTY;

	private Property() {
		Properties property = new Properties();
		try {
			property.load(new FileInputStream(new File(PROPERT_FILE)));
			SERVER_HOST = property.getProperty("SERVER_HOST");
			SERVER_PORT = Integer.parseInt(property.getProperty("SERVER_PORT"));
			KEY_STORE_PATH = property.getProperty("KEY_STORE_PATH");
			KEY_STORE_PASSWORD = property.getProperty("KEY_STORE_PASSWORD");
			LOGGER_FILE = property.getProperty("LOGGER_FILE");
			LOGGER_NAME = property.getProperty("LOGGER_NAME");
			MAIL_PROPERTY = property.getProperty("MAIL_PROPERTY");
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

	public static Property getProperty() {
		if (instance == null)
			instance = new Property();
		return instance;
	}

	public String getSERVER_HOST() {
		return SERVER_HOST;
	}

	public int getSERVER_PORT() {
		return SERVER_PORT;
	}

	public String getKEY_STORE_PATH() {
		return KEY_STORE_PATH;
	}

	public String getKEY_STORE_PASSWORD() {
		return KEY_STORE_PASSWORD;
	}

	public String getMAIL_PROPERTY() {
		return MAIL_PROPERTY;
	}

	
}
