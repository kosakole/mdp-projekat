package org.unibl.etf.mdp.workwithdistributors.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithdistributors.rmi.Distributor;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterial;
import org.unibl.etf.mdp.workwithdistributors.rmi.RawMaterialAmount;
import org.unibl.etf.mdp.workwithdistributors.service.ServerService;

public class CreateOrder extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int WIDTH = 900;
	private static int HEIGHT = 600;
	private static int X = 450;
	private static int Y = 150;
	private static final String TITLE = "Order";

	public CreateOrder() {
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		setVisible(true);
	}
	
	public CreateOrder(ServerService service, Distributor distributor) {
		this();
		this.service = service;
		this.distributor = distributor;
		System.out.println("Ovdje smo");
		service.getDistributors().stream().forEach(System.out::println);
		init();
	}

	private void init() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		listMode = new DefaultListModel<RawMaterialAmount>();
		tfAmount = new JTextField();
		jlistOrder = new JList<RawMaterialAmount>(listMode);
		jlistRawMat = new JList<RawMaterial>(service.getRawMaterialArray(distributor));
		labelProducts.setBounds(5, 10, 150, 20);
		tfAmount.setBounds(WIDTH / 2 - 40, HEIGHT / 2 - 100, 70, 40);
		buttonAdd.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 50, 175, 40);
		jlistRawMat.setBounds(5, 35, WIDTH / 2 - 100, HEIGHT - 70);
		jlistOrder.setBounds(WIDTH / 2 + 100, 35, WIDTH - 570, HEIGHT - 130);
		tfAmount.setText(DEFAULT_AMOUNT);
		butonCreate.setBounds(WIDTH - 170, HEIGHT - 90, 150, 35);
		panel.add(labelProducts);
		panel.add(tfAmount);
		panel.add(buttonAdd);
		panel.add(jlistRawMat);
		panel.add(jlistOrder);
		panel.add(butonCreate);
		//setTable();
		add(panel);
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RawMaterial sel = (RawMaterial)jlistRawMat.getSelectedValue();
				if (sel == null)
					return;
				try {
					RawMaterialAmount pa = new RawMaterialAmount(sel,
							Double.parseDouble(tfAmount.getText()));
					listMode.addElement(pa);
					orders.add(pa);
				} catch (Exception ex) {
					logger.severe(ex.getMessage());
					return;
				}
				tfAmount.setText(DEFAULT_AMOUNT);
				;
			}
		});

		butonCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					service.sendOrder(distributor, orders);
					dispose();
				} catch (Exception e) {
					logger.severe(e.getMessage());
				}

			}
		});

	}
	
	private Logger logger = Property.getProperty().getLogger();

	private JLabel labelProducts = new JLabel("List of raw materials");
	private JPanel panel;
	
	private ServerService service;
	private Distributor distributor;

	private JTextField tfAmount = new JTextField();
	private JList<RawMaterialAmount> jlistOrder;
	private DefaultListModel<RawMaterialAmount> listMode;
	private JList<RawMaterial> jlistRawMat;

	private JButton buttonAdd = new JButton(">>");
	private JButton butonCreate = new JButton("Create order");

//	private List<Product> products = new ArrayList<>();
	private List<RawMaterialAmount> orders = new ArrayList<>();
	private static final String DEFAULT_AMOUNT = "1";
	//private String[] headersProducts = { "Barcode", "Name", "Price" };
}
