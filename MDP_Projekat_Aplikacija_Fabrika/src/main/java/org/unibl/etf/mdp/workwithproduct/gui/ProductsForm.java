package org.unibl.etf.mdp.workwithproduct.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.unibl.etf.mdp.workwithproduct.model.Product;
import org.unibl.etf.mdp.workwithproduct.service.ProductService;

public class ProductsForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int WIDTH = 900;
	private static int HEIGHT = 600;
	private static int X = 450;
	private static int Y = 150;
	private static final String TITLE = "Products";
	
	public ProductsForm() {
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}
	
	private void init() {
		
		panel = new JPanel();
		buttonCreate = new JButton(CREATE);
		buttonUpdate = new JButton(UPDATE);
		buttonDelete = new JButton(DELETE);
		panel.setLayout(null);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		buttonCreate.setBounds(WIDTH - 180, 15, 150, 40);
		buttonUpdate.setBounds(WIDTH - 180, 60, 150, 40);
		buttonDelete.setBounds(WIDTH - 180, 105, 150, 40);
		
		panel.add(buttonCreate);
		panel.add(buttonUpdate);
		panel.add(buttonDelete);
		
		setTable();
		add(panel);
		
		buttonCreate.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CreateUpdateForm();
				dispose();
			}
		});
		
		buttonUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = tableProducts.getSelectedRow();
				if(index < 0)
					return;
				new CreateUpdateForm(products.get(index));
				dispose();
			}
		});
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = tableProducts.getSelectedRow();
				if(index < 0)
					return;
				Product product = new Product();
				product.setBarcode(products.get(index).getBarcode());
				System.out.println(service.delete(product));
//				service.delete(product);
				new ProductsForm().setVisible(true);
				setVisible(false);
				dispose();
			}
		});
	}
	
	private void setTable() {
		products = service.getAll();
		String[][] mat = new String[products.size()][];
		for(int i = 0; i < products.size(); i++) {
			mat[i] = products.get(i).getProductStringArray();
		}
		
		tableProducts = new JTable(mat, headers){
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

	public static void main(String[] args) {
		new ProductsForm().setVisible(true);

	}
	
	private ProductService service = new ProductService();
	private JTable tableProducts;
	private JScrollPane scPane;
	private JPanel panel;

	private JButton buttonCreate;
	private JButton buttonUpdate;
	private JButton buttonDelete;
	
	private List<Product> products;
	private static final String CREATE = "Create";
	private static final String UPDATE = "Update";
	private static final String DELETE = "Delete";
	private String[] headers = {"Barcode", "Name", "Price"};
}
