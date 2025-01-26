package org.unibl.etf.mdp.service;

import java.io.IOException;

import org.unibl.etf.mdp.client.Client;

public class LoginSevice {
	
	private Client client;
	
	public LoginSevice() {
		client = new Client();
	}
	
	public boolean canLogin(String name) throws IOException {
		return client.chackUser(name);
	}
}
