package com.example.chirp.services;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class HelloSenderSessionBean implements HelloSender {
	
	@Resource(name="java:/ConnectionFactory")
	private ConnectionFactory jms;
	
	@Resource(name="java:jboss/exported/jms/queue/test")
	private Queue destination;
	
	/**
	 * @see com.example.chirp.services.HelloSender#sendHelloMessage(java.lang.String)
	 */
	@Override
	public void sendHelloMessage(String text) {
		Connection connection = null;
		try {
			connection = jms.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer sender = session.createProducer(destination);
			TextMessage message = session.createTextMessage(text);
			sender.send(message);
		} catch (JMSException e) {
			close(connection);
		}
	}
	
	public static void close(Connection resource) {
		if (resource == null)
			return;
		try {
			resource.close();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}
	
}











