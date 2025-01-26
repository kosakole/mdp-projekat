package org.unibl.etf.mdp.workwithdistributors.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.unibl.etf.mdp.workwithdistributors.rmi.Distributor;
import org.unibl.etf.mdp.workwithdistributors.service.ServerService;

public class SelectDistributor extends JFrame{
	/**
	 * 
	 */
	
	private static int WIDTH = 370;
	private static int HEIGHT = 150;
	private static int X = 450;
	private static int Y = 150;
	
	private static final long serialVersionUID = 1L;
	
	public SelectDistributor() {
	
		setTitle(TITLE);
		setVisible(true);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);		
	}
	
	public SelectDistributor(ServerService service) {
		this();
		this.service = service;
		init();		
	}
	
	private void init() {
		button = new JButton("Order raw material");
		panel = new JPanel();
		panel.setLayout(null);
		
		if(service.getDistributors() == null || service.getDistributors().size() < 1) {
			cbDistributors = new JComboBox<Distributor>();
		} else {
			cbDistributors = new JComboBox<Distributor>(service.getDistributorsArray());
			cbDistributors.setSelectedIndex(0);
		}
		
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		cbDistributors.setBounds(15, 15, WIDTH -50, 30);
		button.setBounds(WIDTH/2 - 35, 50, WIDTH / 2, 40);
		panel.add(button);
		panel.add(cbDistributors);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Distributor dis = (Distributor)cbDistributors.getSelectedItem();
				if(dis != null) {
					new CreateOrder(service, dis);
					dispose();
				}
					
			}
		});
		
		add(panel);
	}

	private ServerService service;
	private JPanel panel;
	private JComboBox<Distributor> cbDistributors;
	
	private static final String TITLE = "Distributors";
	private JButton button;

}
