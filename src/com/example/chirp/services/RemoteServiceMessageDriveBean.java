package com.example.chirp.services;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import com.example.chirp.model.User;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/test") })
public class RemoteServiceMessageDriveBean implements MessageListener {

	@EJB
	MessageRepository messageRepository;

	@EJB
	UserRepository userRepository;

	@Resource(name = "java:/JmsXA")
	ConnectionFactory jms;

	@Override
	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			String serviceName = objectMessage.getStringProperty("serviceName");
			String methodName = objectMessage.getStringProperty("methodName");
			Object[] parameters = (Object[]) objectMessage.getObject();
			Destination replyQueue = objectMessage.getJMSReplyTo();
			if ("userRepository".equals(serviceName)) {
				if ("findExactlyOneByUserName".equals(methodName)) {
					String userName = (String) parameters[0];
					User result = userRepository
							.findExactlyOneByUserName(userName);
					sendMessage(replyQueue, result);
				}
			}
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage(Destination replyQueue, User result) {
		Connection connection = null;
		try {
			connection = jms.createConnection();
			Session session = connection.createSession(true,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer sender = session.createProducer(replyQueue);
			ObjectMessage message = session.createObjectMessage(result);
			sender.send(message);
		} catch (JMSException e) {
			// Force a rollback
			throw new EJBException(e);
		} finally {
			HelloSenderSessionBean.close(connection);
		}

	}

}
