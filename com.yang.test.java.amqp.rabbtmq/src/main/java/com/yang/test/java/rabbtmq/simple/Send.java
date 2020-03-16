package com.yang.test.java.rabbtmq.simple;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.8.70");
		factory.setUsername("admin");
		factory.setPassword("admin");
		factory.setVirtualHost("/");

		Connection connection = factory.newConnection();

		Channel channel = connection.createChannel();

		channel.queueDeclare(Recv.QUEUE_NAME, false, false, false, null);
		String message = "13";
		channel.basicPublish("", Recv.QUEUE_NAME, null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + message + "'");
		channel.close();
		connection.close();
	}
}