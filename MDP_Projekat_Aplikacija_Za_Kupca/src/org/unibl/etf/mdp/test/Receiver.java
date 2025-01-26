package org.unibl.etf.mdp.test;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

// ova klasa mi je za probu pokretanja mq
// ne koristi se u projektu
public class Receiver {

	private final static String QUEUE_NAME = "test";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String queueName = channel.queueDeclare().getQueue();
		System.out.println("Queue name: " + queueName);

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(body));
				System.out.println(dec.readObject());
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
		
		Thread.sleep(5000);
		channel.abort();
		consumer.handleCancelOk(QUEUE_NAME);
		
	}
}