package org.unibl.etf.mdp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.unibl.etf.mdp.workwithorders.model.*;
import org.unibl.etf.mdp.source.Property;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MainForm extends JFrame {

	/**
	 * 
	 */

	private static int WIDTH = 430;
	private static int HEIGHT = 220;
	private static int X = 450;
	private static int Y = 150;
	private static final String TITLE = "Main";

	private static final long serialVersionUID = 1L;

	public MainForm() throws Exception {
		super();
		setVisible(true);
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}

	public void init() throws Exception {
		panel = new JPanel();
		labelNumberOrder = new JLabel(String.format(FORMAT_LABEL, 0));
		labelInfo = new JLabel(N_REC);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		buttonStart.setBounds(5, 50, 200, 50);
		buttonPause.setBounds(5, 110, 200, 50);
		buttonNextOrder.setBounds(220, 50, 200, 50);
		labelNumberOrder.setBounds(5, 5, 150, 30);
		labelInfo.setBounds(340, 160, 150, 30);
		panel.setLayout(null);
		panel.add(buttonNextOrder);
		panel.add(buttonStart);
		panel.add(buttonPause);
		panel.add(labelNumberOrder);
		panel.add(labelInfo);
		buttonNextOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(orders.size() < 1)
						return;
					new OrdersForm(orders.remove());
					labelNumberOrder.setText(String.format(FORMAT_LABEL, orders.size()));;
				} catch (Exception e1) {
					logger.severe(e1.toString());
				}
			}
		});
		
		buttonStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					initMQ();
					labelInfo.setText(REC);
				} catch (Exception e1) {
					logger.severe(e1.toString());
				}
			}
		});
		
		buttonPause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					channel.close();
					connection.abort();
					consumer.handleCancelOk("");
					labelInfo.setText(N_REC);
				}catch (Exception ex) {
					logger.severe(ex.toString());
				}
			}
		});
		add(panel);
		
	}
	
	private void initMQ() throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		
		connection = factory.newConnection();
		channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String queueName = channel.queueDeclare().getQueue();
		logger.info(queueName + MESSAGE_FOR_START);

		consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(body));
				Order order = (Order)dec.readObject();
				orders.add(order);
				labelNumberOrder.setText(String.format(FORMAT_LABEL, orders.size()));
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
		
	}

	public static void main(String[] args) {
		try {
			new MainForm();
		} catch (Exception e) {
			logger.severe(e.toString());
		}
	}
	
	private static Logger logger = Property.getProperty().getLogger();
	private static String HOST = Property.getProperty().getSERVER_HOST();
	private final static String QUEUE_NAME = "test";

	private JButton buttonNextOrder = new JButton("Next order!");;
	private JButton buttonStart = new JButton("Start receiving");
	private JButton buttonPause = new JButton("Stop receiving");
	private JPanel panel;
	
	private Consumer consumer;
	private Channel channel;
	private Connection connection;
	private Queue<Order> orders = new ArrayDeque<Order>();
	private JLabel labelNumberOrder;
	private JLabel labelInfo;
	private static final String REC = "Receiving...";
	private static final String N_REC = "Not receiving...";
	private static final String FORMAT_LABEL = "Number in queue is: %d";
	private static final String MESSAGE_FOR_START = "start";
}
