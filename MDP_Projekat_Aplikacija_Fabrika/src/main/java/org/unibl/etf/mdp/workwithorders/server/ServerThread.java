package org.unibl.etf.mdp.workwithorders.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.net.ssl.SSLSocket;

import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithorders.service.ServerService;

public class ServerThread extends Thread{
	
	
	private Scanner in;
	private PrintWriter out;
	private static final String MESS_FOR_LOGGIN = "LOG";
	private static final String MESS_FOR_INPUT_ORDERD = "INPUT";
	private static final String CONFIRM_MESS = "OK";
	private static final String NEGATIVE = "NO";
	private static final String PROTOCOL_ERROR = "ERROR...";
	private ServerService service;
	
	ServerThread(SSLSocket socket) {
		super();
		try {
			in = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		service = new ServerService();
		start();
	}
	
	@Override
	public void run() {
		String command;
		try {
			while(true) {
				command = in.nextLine();
				System.out.println(command);
				out.println(CONFIRM_MESS);
				switch(command) {
				case MESS_FOR_LOGGIN:
					if(chackUser(in.nextLine())) 
						out.println(CONFIRM_MESS);
					else 
						out.println(NEGATIVE);
					break;
				case MESS_FOR_INPUT_ORDERD:
					//out.println(CONFIRM_MESS);
					inputOrder(in.nextLine());
					out.println(CONFIRM_MESS);
					break;
				default :
					out.println(PROTOCOL_ERROR);
					break;
				}
			}
		} catch (Exception e) {
			Property.getProperty().getLogger().severe(e.getMessage());
		}
	}
	
	private void inputOrder(String order){
		service.storeOrder(order);
	}
	
	private boolean chackUser(String s) {
		
		return service.checkUserFactory(s);
	}
	
	private void closeResurs() {
		try {
			in.close();
			out.close();
		}catch(Exception ex){
			
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		closeResurs();
	}

}
