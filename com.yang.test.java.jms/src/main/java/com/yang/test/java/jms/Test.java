package com.yang.test.java.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class Test {

	public static void main(String[] args) throws JMSException, InterruptedException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");

		Connection connection = factory.createConnection();
		connection.start();
		
		final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建将要发送的消息
		Message message = session.createTextMessage("Hello JMS!");
		
		// 创建消息的Destination
		Queue queue = new ActiveMQQueue("testQueue");
		

		// 创建消息生产者发送消息
		MessageProducer producer = session.createProducer(queue);
		producer.send(message);
		
		session.close();
		connection.close();

		System.out.println("Send Message Completed!");
		Thread.sleep(100000);
	}
}