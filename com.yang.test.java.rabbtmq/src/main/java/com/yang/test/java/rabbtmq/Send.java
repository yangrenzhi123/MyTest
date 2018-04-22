package com.yang.test.java.rabbtmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {

	private final static String QUEUE_NAME = "hello2";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.1.105");
		factory.setUsername("admin");
		factory.setPassword("admin");

		Connection connection = factory.newConnection();

		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		String message = "Hello World!";

		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));

		System.out.println(" [x] Sent '" + message + "'");

		channel.close();

		connection.close();
	}
}