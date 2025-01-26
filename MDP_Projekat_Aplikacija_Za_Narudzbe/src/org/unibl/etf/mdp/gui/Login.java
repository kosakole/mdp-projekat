package org.unibl.etf.mdp.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.unibl.etf.mdp.service.LoginSevice;
import org.unibl.etf.mdp.source.Property;

public class Login extends JFrame{

	/**
	 * 
	 */
	
	private static int WIDTH = 370;
	private static int HEIGHT = 150;
	private static int X = 450;
	private static int Y = 150;
	
	private static final long serialVersionUID = 1L;
	
	public Login() throws HeadlessException {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		init();
		
	}
	
	private void init() {
		labelUsername = new JLabel(USER_NAME);
		tfUsername = new JTextField();
		buttonLogin = new JButton(LOGIN);
		labelUsername.setBounds(15, 15, 70, 30);
		tfUsername.setBounds(90, 15, 250, 30);
		buttonLogin.setBounds(220, 50, 120 ,40);
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(labelUsername);
		panel.add(tfUsername);
		panel.add(buttonLogin);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		
		buttonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(service.canLogin(tfUsername.getText())) {
						new MainForm().setVisible(true);
						setVisible(false);
						dispose();
					}
				} catch (IOException e1) {
					logger.severe(e1.toString());
				} catch (Exception e1) {
					logger.severe(e1.toString());
				}
				
			}
		});
		
		add(panel);
	}

	public static void main(String[] args) {
		new Login().setVisible(true);
	}
	
	private Logger logger = Property.getProperty().getLogger();
	private LoginSevice service = new LoginSevice();
	
	private JLabel labelUsername;
	private JPanel panel;
	private static String USER_NAME = "User name: ";
	private static String LOGIN = "Login";
	private static final String TITLE = "Login";
	private JTextField tfUsername;
	private JButton buttonLogin;
}
