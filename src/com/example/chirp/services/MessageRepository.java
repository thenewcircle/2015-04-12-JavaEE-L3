package com.example.chirp.services;

import java.util.List;

import com.example.chirp.model.Message;


public interface MessageRepository {

	Message createMessage(Message message);

	Message findExactlyOne(String messageId);

	List<Message> queryByExample(Message example);

	List<Message> queryByExample(Message example, Integer offset, Integer limit);

//	public Message createMessage(String username, String text);
//
//	public Message createMessage(User user, String text);
//
//	public findExactlyOne(Long id) throws NoResultException;
//
//	public findExactlyOneByGuid(String guid) throws NoResultException;
//
//	public List<Message> findMessagesForUser(String username);
	
}
