package org.unibl.etf.mdp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.unibl.etf.mdp.workwithorders.model.*;
import org.unibl.etf.mdp.multicastsocket.MulticastSocketClient;
import org.unibl.etf.mdp.service.ProductService;
import org.unibl.etf.mdp.source.Property;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}

	private void init() {

		panel = new JPanel();
		listMode = new DefaultListModel<ProductAndAmount>();
		listOrder = new JList<ProductAndAmount>(listMode);
		panel.setLayout(null);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		labelProducts.setBounds(5, 10, 100, 20);
		labelMulticastMessage.setBounds(WIDTH / 2 + 100, 10, 300, 20);
		tfAmount.setBounds(WIDTH / 2 - 40, HEIGHT / 2 - 100, 70, 40);
		buttonAdd.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 50, 175, 40);
		buttonMulticast.setBounds(WIDTH / 2 - 90, HEIGHT - 90, 175, 40);
		listOrder.setBounds(WIDTH / 2 + 100, 35, WIDTH - 570, HEIGHT - 130);
		labelEmail.setBounds(5, HEIGHT - 90, 50, 20);
		tfEmail.setBounds(50, HEIGHT - 90, 200, 25);
		tfAmount.setText(DEFAULT_AMOUNT);
		butonCreate.setBounds(WIDTH - 170, HEIGHT - 90, 150, 35);
		panel.add(labelProducts);
		panel.add(tfAmount);
		panel.add(buttonAdd);
		panel.add(listOrder);
		panel.add(labelEmail);
		panel.add(tfEmail);
		panel.add(butonCreate);
		panel.add(buttonMulticast);
		panel.add(labelMulticastMessage);
		setTable();
		add(panel);
		multicast = new MulticastSocketClient(labelMulticastMessage);
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tableProducts.getSelectedRow();
				if (index < 0)
					return;
				try {
					ProductAndAmount pa = new ProductAndAmount(products.get(index),
							Double.parseDouble(tfAmount.getText()));
					listMode.addElement(pa);
					orders.add(pa);
				} catch (Exception ex) {
					logger.severe(e.toString());
					return;
				}
				tfAmount.setText(DEFAULT_AMOUNT);
				;
			}
		});

		butonCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<ProductAndAmount> list = new ArrayList<ProductAndAmount>();
				for (int i = 0; i < listMode.size(); i++) {
					if (list.contains(listMode.get(i))) {
						for (ProductAndAmount pm : list) {
							if (pm.equals(listMode.get(i))) {
								pm.setAmount(pm.getAmount() + listMode.get(i).getAmount());
							}
						}
					} else {
						list.add(listMode.get(i));
					}
				}
				Order order = new Order();
				order.setProducts(list);
				order.setEmail(tfEmail.getText());
				//					File file = new File("objekat.xml");
				//						XMLEncoder enc = new XMLEncoder(new FileOutputStream(file));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				XMLEncoder enc = new XMLEncoder(baos);
				enc.writeObject(order);
				enc.flush();
				enc.close();
								
				try {
					sendToMQ(baos);
				} catch (Exception e) {
					logger.severe(e.toString());
				}
				orders.clear();
				listMode.clear();
				tfEmail.setText("");
			}
		});	
		buttonMulticast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateMulticast(multicast).setVisible(true);;
			}
		});
		initMQ();
	}

	private void sendToMQ(ByteArrayOutputStream baso) throws Exception {
		

		channel.basicPublish("", QUEUE_NAME, null, baso.toByteArray());

//		channel.close();
//		connection.close();
	}
	
	private void initMQ() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			connection = (Connection) factory.newConnection();
			channel = (Channel) connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		} catch (IOException e) {
			logger.severe(e.toString());
		} catch (TimeoutException e) {
			logger.severe(e.toString());
		}
	}

	private void setTable() {

		products = service.getAll();
		String[][] mat = new String[products.size()][];
		for (int i = 0; i < products.size(); i++) {
			mat[i] = products.get(i).getProductStringArray();
		}

		tableProducts = new JTable(mat, headersProducts) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		scPaneProducts = new JScrollPane(tableProducts);
		scPaneProducts.setBounds(5, 35, WIDTH - 550, HEIGHT - 130);
		tableProducts.setBounds(5, 15, WIDTH - 400, HEIGHT - 100);

		panel.add(scPaneProducts);
	}

	public static void main(String[] args) {
		new CreateOrder().setVisible(true);

	}
	
	private Logger logger = Property.getProperty().getLogger();

	private static final String QUEUE_NAME = "test";

	private ProductService service = new ProductService();
	
	private Channel channel;
	private Connection connection;

	private JLabel labelProducts = new JLabel("List of products");
	private JLabel labelEmail = new JLabel("e-mail: ");
	private JTable tableProducts;
	private JScrollPane scPaneProducts;
	private JPanel panel;

	private JTextField tfAmount = new JTextField();
	private JTextField tfEmail = new JTextField();
	private JList<ProductAndAmount> listOrder;
	private DefaultListModel<ProductAndAmount> listMode;

	private JButton buttonAdd = new JButton(">>");
	private JButton butonCreate = new JButton("Create order");

	private List<Product> products = new ArrayList<>();
	private List<ProductAndAmount> orders = new ArrayList<>();
	private static final String DEFAULT_AMOUNT = "1";
	private String[] headersProducts = { "Barcode", "Name", "Price" };
	private JLabel labelMulticastMessage = new JLabel("");
	private MulticastSocketClient multicast;
	private JButton buttonMulticast = new JButton("Multicast message");
}
