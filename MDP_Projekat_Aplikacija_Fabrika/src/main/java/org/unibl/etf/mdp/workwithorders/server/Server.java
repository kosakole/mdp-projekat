package org.unibl.etf.mdp.workwithorders.server;

import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import org.unibl.etf.mdp.source.Property;

public class Server {

	private static int PORT;
	private static String KEY_STORE_PATH;
	private static String KEY_STORE_PASSWORD;
	private static boolean finish = false;
	
	public static void main(String[] args) {
		Property property = Property.getProperty();
		PORT = property.getSERVER_PORT();
		KEY_STORE_PATH = property.getKEY_STORE_PATH();
		KEY_STORE_PASSWORD = property.getKEY_STORE_PASSWORD();
		System.setProperty("javax.net.ssl.keyStore", KEY_STORE_PATH);
		System.setProperty("javax.net.ssl.keyStorePassword", KEY_STORE_PASSWORD);
		
		SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		try(SSLServerSocket ss = (SSLServerSocket) factory.createServerSocket(PORT)){
			while(!finish) {
				SSLSocket socket = (SSLSocket)ss.accept();
				new ServerThread(socket);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
