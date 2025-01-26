package org.unibl.etf.mdp.workwithusers.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Logger;

import org.unibl.etf.mdp.source.Property;

public class MulticastServerSocket extends Thread{

	private static Property property = Property.getProperty();
	private static String HOST;
	private static int PORT;
	private static int SERVER_PORT;
	private static Logger logger;
	
	static {
		HOST = property.getMULTICAST_HOST();
		PORT = property.getMULTICAST_PORT();
		SERVER_PORT = property.getSERVER_PORT();
		logger = property.getLogger();
	}
	public MulticastServerSocket() {
		start();
	}
	
	@Override
	public void run() {
		try (MulticastSocket ms = new MulticastSocket(SERVER_PORT)) {
			InetAddress ia = InetAddress.getByName(HOST);
			ms.setReuseAddress(true);
			byte[] resB = new byte[1024];
			while (true) {
				DatagramPacket res = new DatagramPacket(resB, resB.length);
				ms.receive(res);
				DatagramPacket dp = new DatagramPacket(res.getData(), res.getLength(), ia, PORT);
				ms.send(dp);
			}
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new MulticastServerSocket();
	}
}
