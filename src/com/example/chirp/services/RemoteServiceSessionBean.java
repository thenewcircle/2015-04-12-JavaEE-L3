package com.example.chirp.services;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

@Singleton
public class RemoteServiceSessionBean implements RemoteService {

	@Resource(name = "java:/ConnectionFactory")
	ConnectionFactory jms;
	
	@Resource(name = "java:jboss/exported/jms/queue/test")
	Queue remoteQueue;

	Connection jmsConnection;
	
	@PostConstruct
	public void onStart() throws JMSException {
		jmsConnection = jms.createConnection();
		jmsConnection.start();
	}
	
	/**
	 * @see com.example.chirp.services.RemoteService#sendRemoteRequest(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public void sendRemoteRequest(String serviceName, String methodName, MessageListener replyListener, Object...parameters) {
		try {
			Session session = jmsConnection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			final Queue replyQueue = session.createTemporaryQueue();
			
			MessageConsumer replyReceiver = session.createConsumer(replyQueue);
			replyReceiver.setMessageListener(replyListener);
			
			MessageProducer sender = session.createProducer(remoteQueue);
			ObjectMessage message = session.createObjectMessage(parameters);
			message.setStringProperty("serviceName", serviceName);
			message.setStringProperty("methodName", methodName);
			message.setJMSReplyTo(replyQueue);
			sender.send(message);

		} catch (JMSException e) {
			// Force a rollback
			throw new EJBException(e);
		}
	}

}
