package com.yang.test.java.rabbtmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv2 {

	public final static String QUEUE_NAME = "hello2";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.8.70");
		factory.setUsername("admin");
		factory.setPassword("admin");
		factory.setVirtualHost("/");

		Connection connection = factory.newConnection();

		final Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				while(true) {
					try {
						String message = new String(body, "UTF-8");
						System.out.println(" [x] Received '" + message + "'");

						if(message.equals("02442")) throw new RuntimeException("123");
						
						channel.basicAck(envelope.getDeliveryTag(), true);
					}catch(Exception e) {
						channel.basicCancel(consumerTag);
						
						try {
							Thread.sleep(1000);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		};

		channel.basicConsume(QUEUE_NAME, false, consumer); //false表示手工提交
	}
}