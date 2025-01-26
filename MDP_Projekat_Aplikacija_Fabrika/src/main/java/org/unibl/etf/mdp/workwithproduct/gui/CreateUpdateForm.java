package org.unibl.etf.mdp.workwithproduct.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.unibl.etf.mdp.workwithproduct.model.Product;
import org.unibl.etf.mdp.workwithproduct.source.ProductData;
import org.unibl.etf.mdp.workwithproduct.source.ProductDataRedis;

public class CreateUpdateForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int WIDTH = 300;
	private static int HEIGHT = 250;
	private static int X = 450;
	private static int Y = 150;
	private static final String CREATE = "Create";
	private static final String UPDATE = "Update";
	private boolean isCreate = true;
	
	public CreateUpdateForm() {
		setTitle(CREATE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setVisible(true);
		setLayout(null);
		init();
		button.setText(CREATE);
	}
	
	public CreateUpdateForm(Product product) {
		this();
		setTitle(UPDATE);
		button.setText(UPDATE);
		isCreate = false;
		tfBarcode.setText(product.getBarcode());
		tfName.setText(product.getName());
		tfPrice.setText(product.getPrice()+"");
		oldBarcode = product.getBarcode();
	}
	
	private void init() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		button = new JButton();
		button.setText(CREATE);
		
		labelBarcode.setBounds(15, 15, 50, 20);
		labelName.setBounds(15, 55, 50, 20);
		labelPrice.setBounds(15, 95, 50, 20);
		
		tfBarcode.setBounds(80, 10, 200, 30);
		tfName.setBounds(80, 50, 200, 30);
		tfPrice.setBounds(80, 90, 200, 30);
		
		button.setBounds(175, 125, 105, 50);
		
		panel.add(labelBarcode);
		panel.add(labelName);
		panel.add(labelPrice);
		panel.add(tfBarcode);
		panel.add(tfName);
		panel.add(tfPrice);
		panel.add(button);
		add(panel);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Product product = new Product();
				product.setBarcode(tfBarcode.getText());
				product.setName(tfName.getText());
				product.setPrice(Double.parseDouble(tfPrice.getText()));
				
				if(isCreate) {
					System.out.println(product);
					service.add(product);
				} else {
					service.update(product, oldBarcode);
					System.out.println(product);
				}
				new ProductsForm().setVisible(true);
				dispose();
			}
		});
	}
	
	public static void main(String[] args) {
		new CreateUpdateForm();
	}
	
	private JPanel panel;
	private JLabel labelBarcode = new JLabel("Barcode");
	private JLabel labelName = new JLabel("Name");
	private JLabel labelPrice = new JLabel("Price");
	
	private JTextField tfBarcode = new JTextField();
	private JTextField tfName = new JTextField();
	private JTextField tfPrice = new JTextField();
	
	private JButton button;
	
	private ProductData service = new ProductDataRedis();
	
	private String oldBarcode;
}
