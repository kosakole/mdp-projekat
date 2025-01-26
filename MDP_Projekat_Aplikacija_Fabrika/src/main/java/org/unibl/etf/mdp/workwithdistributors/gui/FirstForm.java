package org.unibl.etf.mdp.workwithdistributors.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.unibl.etf.mdp.workwithdistributors.service.ServerService;


public class FirstForm extends JFrame{

	/**
	 * 
	 */
	
	private static int WIDTH = 370;
	private static int HEIGHT = 150;
	private static int X = 450;
	private static int Y = 150;
	
	private static final long serialVersionUID = 1L;
	
	public FirstForm() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		init();
		
	}
	
	private void init() {
		button = new JButton("Order raw material");
		panel = new JPanel();
		panel.setLayout(null);
		
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		button.setBounds(20, 20, WIDTH-50, HEIGHT-70);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SelectDistributor(service);
			}
		});
		
		add(panel);
	}

	public static void main(String[] args) {
		new FirstForm().setVisible(true);
	}
	
	private ServerService service = new ServerService();
	private JPanel panel;
	private static final String TITLE = "Registration";
	private JButton button;
}
