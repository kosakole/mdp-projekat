package org.unibl.etf.mdp.multicastsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Logger;

import javax.swing.JLabel;

import org.unibl.etf.mdp.source.Property;

public class MulticastSocketClient extends Thread {

	private final static String MCIP;
	private final static int MULTICAST_PORT;
	private final static String SERVER_HOST;
	private final static int SERVER_PORT;
	private MulticastSocket ms;
	private InetAddress ia;
	private JLabel label = new JLabel();
	private static Property property;
	private static Logger logger;
	
	static {
		property = Property.getProperty();
		logger = property.getLogger();
		MCIP = property.getMULTICAST_HOST();
		MULTICAST_PORT = property.getMULTICAST_PORT();
		SERVER_HOST = property.getSERVER_HOST();
		SERVER_PORT = property.getSERVER_PORT();
	}
	public MulticastSocketClient() {
		try {
			ms = new MulticastSocket(MULTICAST_PORT);
			ia = InetAddress.getByName(MCIP);
			logger.info(START_STRING);
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
		start();
	}

	public MulticastSocketClient(JLabel label) {
		this();
		this.label = label;
	}

	@Override
	public void run() {
		byte[] buff = new byte[256];
		try {

			ms.setReuseAddress(true);
			ms.joinGroup(ia);
			while (true) {
				DatagramPacket dp = new DatagramPacket(buff, buff.length);
				ms.receive(dp);
				String mes = new String(dp.getData());
				label.setText(mes);
			}
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
	}

	public void sendMessage(String s) {
		byte[] mes = s.getBytes();

		try {
			DatagramPacket dp = new DatagramPacket(mes, mes.length, InetAddress.getByName(SERVER_HOST), SERVER_PORT);
			ms.send(dp);
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
	}

	private static String START_STRING = "Multicast start successfully.";
}
