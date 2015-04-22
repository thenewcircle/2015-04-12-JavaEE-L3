package com.example.chirp.rest;

import javax.ws.rs.core.Request;

public class MessageResource {
	/**
	 * <p>
	 * A user creates a message.
	 * </p>
	 * <code>POST /messages</code>
	 */
	public Request createMessage() {
		return null;
	}

	/**
	 * <p>
	 * Retrieve a message
	 * </p>
	 * <code>GET /messages/xsdsd</code>
	 */
	public Request getMessage() {
		return null;
	}

	/**
	 * <p>
	 * Retrieve all messages for user.
	 * </p>
	 * <code>GET /messages?username=dbateman</code>
	 */
	public Request findMessages() {
		return null;
	}

	/**
	 * <p>
	 * Delete a message.
	 * </p>
	 * <code>DELETE /messages/xsdsd</code>
	 */
	public Request deleteMessage() {
		return null;
	}

}
