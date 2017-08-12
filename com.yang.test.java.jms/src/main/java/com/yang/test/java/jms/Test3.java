package com.yang.test.java.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class Test3 {

	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");

		Connection connection = factory.createConnection();
		connection.start();
		
		final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Queue queue = new ActiveMQQueue("testQueue");
		
		// 创建消息的接收者
		MessageConsumer comsumer = session.createConsumer(queue);
		// 消息的消费者接收消息的第一种方式：consumer.receive() 或 consumer.receive(int timeout)；
		// Message recvMessage = comsumer.receive();
		// System.out.println(((TextMessage) recvMessage).getText());
		// 消息的消费者接收消息的第二种方式：注册一个MessageListener
		comsumer.setMessageListener(new MessageListener() {
			public void onMessage(Message msg) {
				TextMessage textMsg = (TextMessage) msg;
				try {
					System.out.println(textMsg.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		
	}
}
