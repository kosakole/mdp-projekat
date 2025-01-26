package org.unibl.etf.mdp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.unibl.etf.mdp.workwithorders.model.*;
import org.unibl.etf.mdp.service.OrderService;


public class OrdersForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int WIDTH = 900;
	private static int HEIGHT = 600;
	private static int X = 450;
	private static int Y = 150;
	private static final String TITLE = "Products";
	
	public OrdersForm(Order order2) throws Exception{
		this.order = order2;
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		init();		
	}
	
	private void init() {
		
		panel = new JPanel();
		buttonApprove = new JButton(APPROVE);
		buttonReject = new JButton(REJECT);
		panel.setLayout(null);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		buttonApprove.setBounds(WIDTH - 180, 15, 150, 40);
		buttonReject.setBounds(WIDTH - 180, 60, 150, 40);
		
		
		panel.add(buttonApprove);
		panel.add(buttonReject);
		
		
		add(panel);
		
		buttonApprove.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OrderFF send = new OrderFF();
				send.setOrder(order);
				send.setStatus(OrderStatus.APPROVED);
				service.sendOrder(send);
				dispose();
			}
		});
		
		buttonReject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OrderFF send = new OrderFF();
				send.setOrder(order);
				send.setStatus(OrderStatus.REJECTED);
				service.sendOrder(send);
				dispose();
			}
		});
		
		setTable();
	}
	
	private void setTable() {
		
		if(order == null)
			return;
		String[][] mat = new String[order.getProducts().size()][];
		
		for(int i = 0; i < order.getProducts().size(); i++) {
			mat[i] = order.getProducts().get(i).getArray();
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
	
	private OrderService service = new OrderService();
	
	private JTable tableProducts;
	private JScrollPane scPane;
	private JPanel panel;

	private JButton buttonApprove;
	private JButton buttonReject;

	
	private Order order;
	private static final String APPROVE = "Approve";
	private static final String REJECT = "Reject";
	private String[] headers = {"Barcode", "Name", "Price", "Amount", "Total"};
	
}
