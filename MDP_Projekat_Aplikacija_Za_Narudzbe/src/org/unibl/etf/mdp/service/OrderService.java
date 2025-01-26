package org.unibl.etf.mdp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.unibl.etf.mdp.client.Client;
import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithorders.model.OrderFF;

import com.google.gson.Gson;

public class OrderService {

	private Client client;
	private String username;
	private String password;
	
	private static final String ORDER_TITLE = "Narudzba";
	private static final String MAIL_SENT = "Mail sent";
	private static Property property;
	private static Logger logger;
	
	static {
		property = Property.getProperty();
		logger = property.getLogger();
	}
	
	public OrderService() {
		client = new Client();
		try {
			loadMailConfig();
		} catch (FileNotFoundException e) {
			logger.severe(e.toString());
		} catch (IOException e) {
			logger.severe(e.toString());
		}
	}
	
	public void sendOrder(OrderFF order) {
		Gson gson = new Gson();
		String s = gson.toJson(order);
		client.sendOrder(s);
		sendMail(order.getOrder().getEmail(), order.getStatus().toString());
	}
	
	public boolean sendMail(String to, String text) {
		try {
			return sendMail(to, ORDER_TITLE, text);
		} catch (FileNotFoundException e) {
			logger.severe(e.toString());
		} catch (IOException e) {
			logger.severe(e.toString());
		}
		return false;
	}
	
	private Properties loadMailConfig() throws FileNotFoundException, IOException {
		String mailPoperty = property.getMAIL_PROPERTY();
	
		Properties mailProp = new Properties();
		mailProp.load(new FileInputStream(new File(mailPoperty)));

		username = mailProp.getProperty("username");
		password = mailProp.getProperty("password");
		return mailProp;
	}
	
	private boolean sendMail(String to, String title, String body) throws FileNotFoundException, IOException {

		System.out.println("OVdje je dosao!!!");
		Properties props = loadMailConfig();

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(title);
			message.setText(body);
			Transport.send(message);
			logger.info(MAIL_SENT);
			return true;
		} catch (MessagingException e) {
			logger.severe(e.toString());
			return false;
		}
	}
}
