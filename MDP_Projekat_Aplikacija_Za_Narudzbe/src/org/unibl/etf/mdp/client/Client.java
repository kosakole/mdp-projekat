package org.unibl.etf.mdp.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.unibl.etf.mdp.source.Property;

public class Client {

	private static String HOST;
	private static int PORT;
	private static String KEY_STORE_PATH;
	private static String KEY_STORE_PASSWORD;
	private SSLSocket ss;
	private Scanner in;
	private PrintWriter out;
	private static String MESS_FOR_LOGGIN = "LOG";
	private static String CONFIRM_MESS = "OK";
	private static String MESS_FOR_SEND_ORDER = "INPUT";
	private static String SERVER_ERROR = "Server error!";
	private static String CONNECTION_SUCCESSFULL = "Connecton successfull!";
	private static Logger logger;

	public Client() {
		Property property = Property.getProperty();
		logger = property.getLogger();
		HOST = property.getSERVER_HOST();
		PORT = property.getSERVER_PORT();
		KEY_STORE_PATH = property.getKEY_STORE_PATH();
		KEY_STORE_PASSWORD = property.getKEY_STORE_PASSWORD();
		System.setProperty("javax.net.ssl.trustStore", KEY_STORE_PATH);
		System.setProperty("javax.net.ssl.trustStorePassword", KEY_STORE_PASSWORD);

		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try {
			ss = (SSLSocket) factory.createSocket(HOST, PORT);
			in = new Scanner(new BufferedReader(new InputStreamReader(ss.getInputStream())));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(ss.getOutputStream())), true);
			logger.info(CONNECTION_SUCCESSFULL);
		} catch (IOException e) {
			logger.severe(e.toString());
		}
	}

	public boolean chackUser(String username) throws IOException {
		out.println(MESS_FOR_LOGGIN);
		if (!CONFIRM_MESS.equals(in.nextLine())) {
			throw new IOException(SERVER_ERROR);
		}

		out.println(username);
		String com = in.nextLine();
		System.out.println(com);
		if (CONFIRM_MESS.equals(com))
			return true;
		return false;
	}

	public void sendOrder(String order) {
		out.println(MESS_FOR_SEND_ORDER);
		out.println(order);
	}

	public void closeResurse() {
		in.close();
		out.close();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		closeResurse();
	}
}
