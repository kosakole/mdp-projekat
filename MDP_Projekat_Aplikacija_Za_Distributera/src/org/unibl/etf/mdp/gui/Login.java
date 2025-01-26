package org.unibl.etf.mdp.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		buttonLogin = new JButton(REGISTRATION);
		labelUsername.setBounds(5, 15, 70, 30);
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
				if(tfUsername.getText().length() > 0) {
					new MainForm(tfUsername.getText());
					dispose();
				}
			}
		});
		
		add(panel);
	}

	public static void main(String[] args) {
		new Login().setVisible(true);
	}
	
	
	private JLabel labelUsername;
	private JPanel panel;
	private static String USER_NAME = "User name: ";
	private static String REGISTRATION = "Registration";
	private static final String TITLE = "Registration";
	private JTextField tfUsername;
	private JButton buttonLogin;
}
