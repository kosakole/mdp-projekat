package org.unibl.etf.mdp.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.unibl.etf.mdp.multicastsocket.MulticastSocketClient;


public class CreateMulticast extends JFrame{

	/**
	 * 
	 */
	
	private static int WIDTH = 370;
	private static int HEIGHT = 150;
	private static int X = 450;
	private static int Y = 150;
	
	private static final long serialVersionUID = 1L;
	
	public CreateMulticast(MulticastSocketClient ms) throws HeadlessException {
		super();
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		init();
		this.ms = ms;
	}
	
	private void init() {
		labelUsername = new JLabel(Message);
		tfMulticasMess = new JTextField();
		buttonLogin = new JButton(ButtonText);
		labelUsername.setBounds(15, 15, 70, 30);
		tfMulticasMess.setBounds(90, 15, 250, 30);
		buttonLogin.setBounds(220, 50, 120 ,40);
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(labelUsername);
		panel.add(tfMulticasMess);
		panel.add(buttonLogin);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		
		buttonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ms.sendMessage(tfMulticasMess.getText());
			}
		});
		
		add(panel);
	}

	private JLabel labelUsername;
	private JPanel panel;
	private static String Message = "Message: ";
	private static String ButtonText = "Send";
	private static final String TITLE = "Multicast message";
	private JTextField tfMulticasMess;
	private JButton buttonLogin;
	private MulticastSocketClient ms;
}
