package com.example.chirp.services;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//@MessageDriven(activationConfig = {
//		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
//		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/test") })
public class HelloMessageDrivenBean implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			System.err.println("Message Received: " + text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
