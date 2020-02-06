package com.yang.test.java.rabbtmq.routing;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "my-test-channel";

    public static void main(String[] argv)
                  throws java.io.IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("172.28.51.33");
		factory.setUsername("test");
		factory.setPassword("test");
		factory.setVirtualHost("test");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String severity = "123";
        String message = "456";

        channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
        System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

        channel.close();
        connection.close();
    }
    //..
}