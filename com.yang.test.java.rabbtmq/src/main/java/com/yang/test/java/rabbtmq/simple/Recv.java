package com.yang.test.java.rabbtmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv {

	public final static String QUEUE_NAME = "hello2";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();

		factory.setHost("172.28.51.33");
		factory.setUsername("test");
		factory.setPassword("test");
		factory.setVirtualHost("test");

		Connection connection = factory.newConnection();

		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
				throw new RuntimeException("123");
			}
		};

		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}