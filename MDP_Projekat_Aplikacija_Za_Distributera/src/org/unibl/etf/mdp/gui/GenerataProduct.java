package org.unibl.etf.mdp.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.unibl.etf.mdp.service.ProductService;
import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterial;

public class GenerataProduct extends JFrame{


	/**
	 * 
	 */
	
	private static int WIDTH = 370;
	private static int HEIGHT = 200;
	private static int X = 450;
	private static int Y = 150;
	
	private static final long serialVersionUID = 1L;
	
	public GenerataProduct() throws HeadlessException {
		super();
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		init();
	}
	
	public GenerataProduct(ProductService service) {
		this();
		this.service = service;
	}
	
	private void init() {
		labelProduct = new JLabel(PRODUCT);
		tfProduct = new JTextField();
		tfPrice = new JTextField();
		buttonLogin = new JButton(GENERATE);
		labelProduct.setBounds(5, 15, 70, 30);
		labelPrice.setBounds(5, 50, 70, 30);
		tfProduct.setBounds(90, 15, 250, 30);
		tfPrice.setBounds(90, 50, 250, 30);
		buttonLogin.setBounds(220, 85, 120 ,40);
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(labelProduct);
		panel.add(labelPrice);
		panel.add(tfProduct);
		panel.add(tfPrice);
		panel.add(buttonLogin);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		
		buttonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RawMaterial rawM = new RawMaterial();
				rawM.setName(tfProduct.getText());
				rawM.setPrice(Double.parseDouble(tfPrice.getText()));
				try {
					service.generateProduct(rawM);
					logger.info(GENERATE);
				} catch (RemoteException e1) {
					logger.severe(e1.getMessage());
				}
				tfPrice.setText("");
				tfProduct.setText("");
			}
		});
		
		add(panel);
	}

	private Logger logger = Property.getProperty().getLogger();
	private ProductService service;
	private JLabel labelProduct;
	private JLabel labelPrice = new JLabel("Price");
	private JPanel panel;
	private static String PRODUCT = "Product: ";
	private static String GENERATE = "Generate";
	private static final String TITLE = "Generate product";
	private JTextField tfProduct;
	private JTextField tfPrice;
	private JButton buttonLogin;
}
