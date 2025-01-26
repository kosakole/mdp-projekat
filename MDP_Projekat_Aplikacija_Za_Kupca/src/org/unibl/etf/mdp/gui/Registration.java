package org.unibl.etf.mdp.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.unibl.etf.mdp.service.UserService;
import org.unibl.etf.mdp.workwithusers.model.User;
import org.unibl.etf.mdp.workwithusers.model.UserStatus;

public class Registration extends JFrame{

	/**
	 * 
	 */
	
	private static int WIDTH = 400;
	private static int HEIGHT = 400;
	private static int X = 450;
	private static int Y = 150;
	
	private static final long serialVersionUID = 1L;

	public Registration() throws HeadlessException {
		super();
		setVisible(true);
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		init();
		service = new UserService();
	}
	
	private void init() {
		labelNameCompany = new JLabel(NAME_COMPANY);
		labelAddress = new JLabel(ADDRESS);
		labelPhoneNumber = new JLabel(PHONE_NUMBER);
		labelUsername = new JLabel(USERNAME);
		labelPassword1 = new JLabel(PASSWORD);
		labelPassword2 = new JLabel(REPEATE_PASSWORD);
		
		panel = new JPanel();
		
		tfNameCompany = new JTextField();
		tfAddress = new JTextField();
		tfPhoneNumber = new JTextField();
		tfUsername = new JTextField();
		tfPassword1 = new JPasswordField();
		tfPassword2 = new JPasswordField();
		
		buttonRegistration = new JButton(REGISTRATOR);

		panel.setLayout(null);
		
		labelNameCompany.setBounds(15, 15, 130, 30);
		labelAddress.setBounds(15, 55, 130, 30);
		labelPhoneNumber.setBounds(15, 95, 130, 30);
		labelUsername.setBounds(15, 135, 130, 30);
		labelPassword1.setBounds(15, 175, 130, 30);
		labelPassword2.setBounds(15, 215, 130, 30);
		
		tfNameCompany.setBounds(150, 15, 200, 30);;
		tfAddress.setBounds(150, 55, 200, 30);;
		tfPhoneNumber.setBounds(150, 95, 200, 30);;
		tfUsername.setBounds(150, 135, 200, 30);;
		tfPassword1.setBounds(150, 175, 200, 30);;
		tfPassword2.setBounds(150, 215, 200, 30);;
		
		buttonRegistration.setBounds(230, 250, 120, 40); 
		
		panel.add(labelNameCompany);
		panel.add(labelAddress);
		panel.add(labelPhoneNumber);
		panel.add(labelUsername);
		panel.add(labelPassword1);
		panel.add(labelPassword2);
		
		panel.add(tfNameCompany);
		panel.add(tfAddress);
		panel.add(tfPhoneNumber);
		panel.add(tfUsername);
		panel.add(tfPassword1);
		panel.add(tfPassword2);
		
		panel.add(buttonRegistration);
		
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		
		add(panel);
		
		buttonRegistration.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setUsername(tfUsername.getText());
				user.setAddress(tfAddress.getText());
				user.setCompanyName(tfNameCompany.getText());
				user.setPassword(tfPassword1.getText());
				user.setPhone(tfPhoneNumber.getText());
				user.setStatus(UserStatus.BLOCKED);
				if(!tfPassword1.getText().equals(tfPassword2.getText()) || tfPassword1.getText().length() < 2) {
					JOptionPane.showMessageDialog(new JFrame(), ERROR_PASSWORD, ERROR,JOptionPane.ERROR_MESSAGE);
					return;
				}
				service.add(user);
				setVisible(false);
			}
		});
	}
	
	private UserService service;
	private JLabel labelNameCompany;
	private JLabel labelAddress;
	private JLabel labelPhoneNumber;
	private JLabel labelUsername;
	private JLabel labelPassword1;
	private JLabel labelPassword2;
	
	private JPanel panel;
	
	private JTextField tfNameCompany;
	private JTextField tfAddress;
	private JTextField tfPhoneNumber;
	private JTextField tfUsername;
	private JPasswordField tfPassword1;
	private JPasswordField tfPassword2;
	
	private JButton buttonRegistration;
	
	private static String NAME_COMPANY = "Name of company";
	private static String ADDRESS = "Address";
	private static String PHONE_NUMBER = "Phone number";
	private static String USERNAME = "Username";
	private static String PASSWORD = "Password";
	private static String REPEATE_PASSWORD = "Password repeate";
	private static String REGISTRATOR = "Registration";
	private static String TITLE = REGISTRATOR;
	private static String ERROR_PASSWORD = "Password not correct!";
	private static String ERROR = "ERROR";
}
