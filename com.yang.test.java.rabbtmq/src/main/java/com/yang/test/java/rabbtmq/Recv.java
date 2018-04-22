package com.yang.test.java.rabbtmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv {

	private final static String QUEUE_NAME = "hello2";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();

		factory.setHost("192.168.1.105");
		factory.setUsername("admin");
		factory.setPassword("admin");

		Connection connection = factory.newConnection();

		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {

			@Override

			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body)

					throws IOException {

				String message = new String(body, "UTF-8");

				System.out.println(" [x] Received '" + message + "'");

			}

		};

		channel.basicConsume(QUEUE_NAME, true, consumer);

	}

}