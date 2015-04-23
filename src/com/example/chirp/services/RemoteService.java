package com.example.chirp.services;

import javax.jms.MessageListener;


public interface RemoteService {

	void sendRemoteRequest(String serviceName, String methodName,
			MessageListener replyListener, Object... parameters);

}