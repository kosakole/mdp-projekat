package org.unibl.etf.mdp.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.unibl.etf.mdp.dto.UserDTOLogin;
import org.unibl.etf.mdp.service.UserService;

public class Login extends JFrame{

	/**
	 * 
	 */
	
	private static int WIDTH = 370;
	private static int HEIGHT = 200;
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
		labelPassword = new JLabel(PASSWORD);
		tfUsername = new JTextField();
		pfPassword = new JPasswordField();
		buttonLogin = new JButton(LOGIN);
		buttonRegistration = new JButton(REGISTRATION);
		labelUsername.setBounds(15, 15, 70, 30);
		labelPassword.setBounds(15, 55, 70, 30);
		tfUsername.setBounds(90, 15, 250, 30);
		pfPassword.setBounds(90, 55, 250, 30);
		buttonLogin.setBounds(220, 90, 120 ,40);
		buttonRegistration.setBounds(90, 90, 120, 40);
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(labelUsername);
		panel.add(labelPassword);
		panel.add(tfUsername);
		panel.add(pfPassword);
		panel.add(buttonLogin);
		panel.add(buttonRegistration);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		buttonLogin.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = tfUsername.getText();
				@SuppressWarnings( "deprecation" )
				String pass = pfPassword.getText();
				if(username.length() > 0 && pass.length() > 0) {
					if(service.checkUser(new UserDTOLogin(username, pass))) {
						new CreateOrder().setVisible(true);
						dispose();
					}
				}
				
			}
		});
		buttonRegistration.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Registration();
			}
		}
		);
		add(panel);
	}

	public static void main(String[] args) {
		new Login().setVisible(true);
	}
	
	private UserService service = new UserService();
	
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JPanel panel;
	private static String USER_NAME = "User name: ";
	private static String PASSWORD = "Password: ";
	private static String LOGIN = "Login";
	private static String REGISTRATION = "Registration";
	private static final String TITLE = "Login";
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JButton buttonLogin;
	private JButton buttonRegistration;
}
