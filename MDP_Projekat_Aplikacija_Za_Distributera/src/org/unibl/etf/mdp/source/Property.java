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
	private String RESOURSE_PATH;
	private String CLIENT_POLICY;
	private String SERVER_POLICY;
	private String RMI_HOST;
	private String ServerRMI;
	
	private String SERVER_HOST;
	private int RMI_PORT;

	private Property() {
		Properties property = new Properties();
		try {
			property.load(
					new FileInputStream(new File(PROPERT_FILE)));
			LOGGER_FILE = property.getProperty("LOGGER_FILE");
			LOGGER_NAME = property.getProperty("LOGGER_NAME");
			SERVER_HOST = property.getProperty("SERVER_HOST");
			RMI_PORT = Integer.parseInt(property.getProperty("RMI_PORT"));
			RESOURSE_PATH = property.getProperty("RESOURSE_PATH");
			CLIENT_POLICY = property.getProperty("CLIENT_POLICY");
			SERVER_POLICY = property.getProperty("SERVER_POLICY");
			RMI_HOST = property.getProperty("RMI_HOST");
			ServerRMI = property.getProperty("ServerRMI");
			initLogger();
		} catch (IOException e) {
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

	public String getSERVER_HOST() {
		return SERVER_HOST;
	}

	public int getRMI_PORT() {
		return RMI_PORT;
	}

	public static Property getProperty() {
		if (instance == null)
			instance = new Property();
		return instance;
	}

	public String getRESOURSE_PATH() {
		return RESOURSE_PATH;
	}

	public String getCLIENT_POLICY() {
		return CLIENT_POLICY;
	}

	public String getSERVER_POLICY() {
		return SERVER_POLICY;
	}

	public String getRMI_HOST() {
		return RMI_HOST;
	}

	public String getServerRMI() {
		return ServerRMI;
	}
	
	
}
