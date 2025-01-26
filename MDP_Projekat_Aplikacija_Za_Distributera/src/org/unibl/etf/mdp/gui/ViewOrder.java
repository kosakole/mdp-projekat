package org.unibl.etf.mdp.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.unibl.etf.mdp.service.ProductService;

public class ViewOrder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int WIDTH = 900;
	private static int HEIGHT = 600;
	private static int X = 450;
	private static int Y = 150;
	private static final String TITLE = "Products";

	public ViewOrder() {
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		setVisible(true);
	}

	public ViewOrder(ProductService service, String factory) {
		this();
		this.service = service;
		this.factory = factory;
		init();
	}

	private void init() {

		panel = new JPanel();

		panel.setLayout(null);
		panel.setBounds(0, 0, WIDTH, HEIGHT);

		add(panel);

		setTable();
	}

	private void setTable() {
		String[][] mat = service.getOrderMat(factory);

		tableProducts = new JTable(mat, headers) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		scPane = new JScrollPane(tableProducts);
		scPane.setBounds(5, 15, WIDTH - 200, HEIGHT - 100);
		tableProducts.setBounds(5, 15, WIDTH - 200, HEIGHT - 100);
		panel.add(scPane);
	}

	private String factory;
	private ProductService service;
	private JTable tableProducts;
	private JScrollPane scPane;
	private JPanel panel;

	private String[] headers = { "Name", "Amount" };

}
