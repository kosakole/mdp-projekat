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
	public static String FILE_USERS;
	public static Property instance = null;
	private String LOGGER_FILE;
	private Logger logger;
	private String HOST_REDIS;
	private int PORT_REDIS;
	private String REDITS_NAME;
	private char SPLIT_REDIS_SPACE;
	private char DELIMITER_REDIS;
	private String SERVER_HOST;
	private int SERVER_PORT;
	private String KEY_STORE_PATH;
	private String KEY_STORE_PASSWORD;
	private String FILE_USERS_FACTORY;
	private String LOGGER_NAME;
	private String MULTICAST_HOST;
	private int MULTICAST_PORT;
	private String RMI_HOST;
	private int RMI_PORT;
	private String RESOURSE_PATH;
	private String CLIENT_POLICY;
	private String SERVER_POLICY;
	private String ServerRMI;
	private String SERVER_NAME;
	private String FOLDER_ORDERS;
	private String DATA_TIME_FORMAT_FOR_FILE;

	private Property() {
		Properties property = new Properties();
		try {
			property.load(
					new FileInputStream(new File(PROPERT_FILE)));
			HOST_REDIS = property.getProperty("HOST_REDIS");
			PORT_REDIS = Integer.parseInt(property.getProperty("PORT_REDIS"));
			MULTICAST_HOST = property.getProperty("MULTICAST_HOST");
			MULTICAST_PORT = Integer.parseInt(property.getProperty("MULTICAST_PORT"));
			REDITS_NAME = property.getProperty("REDITS_NAME");
			SPLIT_REDIS_SPACE = property.getProperty("SPLIT_REDIS_SPACE").charAt(0);
			DELIMITER_REDIS = property.getProperty("DELIMITER_REDIS").charAt(0);
			SERVER_HOST = property.getProperty("SERVER_HOST");
			SERVER_PORT = Integer.parseInt(property.getProperty("SERVER_PORT"));
			KEY_STORE_PATH = property.getProperty("KEY_STORE_PATH");
			KEY_STORE_PASSWORD = property.getProperty("KEY_STORE_PASSWORD");
			FILE_USERS_FACTORY = property.getProperty("FILE_USERS_FACTORY");
			FILE_USERS = property.getProperty("FILE_USERS");
			LOGGER_FILE = property.getProperty("LOGGER_FILE");
			LOGGER_NAME = property.getProperty("LOGGER_NAME");
			RMI_HOST = property.getProperty("RMI_HOST");
			RMI_PORT = Integer.parseInt(property.getProperty("RMI_PORT"));
			RESOURSE_PATH = property.getProperty("RESOURSE_PATH");
			CLIENT_POLICY = property.getProperty("CLIENT_POLICY");
			SERVER_POLICY = property.getProperty("SERVER_POLICY");
			ServerRMI = property.getProperty("ServerRMI");
			SERVER_NAME = property.getProperty("SERVER_NAME");
			FOLDER_ORDERS = property.getProperty("FOLDER_ORDERS");
			DATA_TIME_FORMAT_FOR_FILE = property.getProperty("DATA_TIME_FORMAT_FOR_FILE");
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

	public static Property getProperty() {
		if (instance == null)
			instance = new Property();
		return instance;
	}

	public String getHOST_REDIS() {
		return HOST_REDIS;
	}

	public void setHOST_REDIS(String hOST_REDIS) {
		HOST_REDIS = hOST_REDIS;
	}

	public int getPORT_REDIS() {
		return PORT_REDIS;
	}

	public void setPORT_REDIS(int pORT_REDIS) {
		PORT_REDIS = pORT_REDIS;
	}

	public String getREDITS_NAME() {
		return REDITS_NAME;
	}

	public void setREDITS_NAME(String rEDITS_NAME) {
		REDITS_NAME = rEDITS_NAME;
	}

	public char getSPLIT_REDIS_SPACE() {
		return SPLIT_REDIS_SPACE;
	}

	public void setSPLIT_REDIS_SPACE(char sPLIT_REDIS_SPACE) {
		SPLIT_REDIS_SPACE = sPLIT_REDIS_SPACE;
	}

	public char getDELIMITER_REDIS() {
		return DELIMITER_REDIS;
	}

	public void setDELIMITER_REDIS(char dELIMITER_REDIS) {
		DELIMITER_REDIS = dELIMITER_REDIS;
	}

	public static void main(String[] args) {

		System.out.println(new Property().HOST_REDIS);
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

	public String getFILE_USERS_FACTORY() {
		return FILE_USERS_FACTORY;
	}

	public void setFILE_USERS_FACTORY(String fILE_USERS_FACTORY) {
		FILE_USERS_FACTORY = fILE_USERS_FACTORY;
	}

	public String getFileUsers() {
		return FILE_USERS;
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

	public String getRMI_HOST() {
		return RMI_HOST;
	}

	public int getRMI_PORT() {
		return RMI_PORT;
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

	public String getServerRMI() {
		return ServerRMI;
	}

	public String getSERVER_NAME() {
		return SERVER_NAME;
	}

	public String getFOLDER_ORDERS() {
		return FOLDER_ORDERS;
	}

	public String getDATA_TIME_FORMAT_FOR_FILE() {
		return DATA_TIME_FORMAT_FOR_FILE;
	}

	
}
