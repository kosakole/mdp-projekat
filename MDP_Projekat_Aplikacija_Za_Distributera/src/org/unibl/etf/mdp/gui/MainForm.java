package org.unibl.etf.mdp.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.unibl.etf.mdp.service.ProductService;
import org.unibl.etf.mdp.workwithdistributors.rmi.Distributor;


public class MainForm extends JFrame{

	/**
	 * 
	 */
	
	private static int WIDTH = 450;
	private static int HEIGHT = 150;
	private static int X = 450;
	private static int Y = 150;
	
	private static final long serialVersionUID = 1L;
	
	public MainForm() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		init();	
	}
	
	public MainForm(String name) {
		this();
		this.service = new ProductService(new Distributor(name));
	}
	
	private void init() {
		buttonGenProduct = new JButton("Generate products");
		buttonViewOrders = new JButton("View orders");
		panel = new JPanel();
		panel.setLayout(null);
		
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		buttonGenProduct.setBounds(20, 20, WIDTH / 2 -40, HEIGHT - 70);
		buttonViewOrders.setBounds(WIDTH / 2 +20 , 20, WIDTH /2 -40, HEIGHT - 70);
		panel.add(buttonGenProduct);
		panel.add(buttonViewOrders);
		buttonGenProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new GenerataProduct(service).setVisible(true);;
			}
		});
		
		buttonViewOrders.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Service je null: " + service == null);
				new SelectOrder(service).setVisible(true);;
			}
		});
		
		add(panel);
	}

	
	private ProductService service;
	private JPanel panel;
	private static final String TITLE = "Registration";
	private JButton buttonGenProduct;
	private JButton buttonViewOrders;
}
