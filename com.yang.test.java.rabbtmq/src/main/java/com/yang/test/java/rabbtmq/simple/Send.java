package com.yang.test.java.rabbtmq.simple;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("172.28.51.33");
		factory.setUsername("test");
		factory.setPassword("test");
		factory.setVirtualHost("test");

		Connection connection = factory.newConnection();

		Channel channel = connection.createChannel();

		channel.queueDeclare(Recv.QUEUE_NAME, false, false, false, null);
		String message = "Hello World2";
		channel.basicPublish("", Recv.QUEUE_NAME, null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + message + "'");
		channel.close();
		connection.close();
	}
}