package org.unibl.etf.mdp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.unibl.etf.mdp.service.ProductService;

public class SelectOrder extends JFrame{
	/**
	 * 
	 */
	
	private static int WIDTH = 370;
	private static int HEIGHT = 150;
	private static int X = 450;
	private static int Y = 150;
	
	private static final long serialVersionUID = 1L;
	
	public SelectOrder() {
	
		setTitle(TITLE);
		setVisible(true);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);	
	}
	
	public SelectOrder(ProductService service) {
		this();
		this.service = service;
		init();
	}
	private void init() {
		button = new JButton("Order raw material");
		
		service.getOrders().stream().forEach(System.out::println);
		if(service.getOrdersArray() == null) {
			System.out.println("Vratice null");
			cbFactory = new JComboBox<String>();
		}
			
		else
			cbFactory = new JComboBox<String>(service.getOrdersArray());
		panel = new JPanel();
		panel.setLayout(null);
		
		
		cbFactory.setBounds(15, 15, WIDTH -50, 30);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		button.setBounds(WIDTH/2 - 35, 50, WIDTH / 2, 40);
		panel.add(button);
		panel.add(cbFactory);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewOrder(service, cbFactory.getSelectedItem()+"").setVisible(true);
			}
		});
		
		add(panel);
	}

	public static void main(String[] args) {
		new SelectOrder().setVisible(true);
	}
	
	private ProductService service;
	private JPanel panel;
	private JComboBox<String> cbFactory;
	
	private static final String TITLE = "Distributors";
	private JButton button;

}
