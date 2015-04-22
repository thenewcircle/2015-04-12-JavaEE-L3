package com.example.chirp.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MessageResource {
	/**
	 * <p>
	 * A user creates a message.
	 * </p>
	 * <code>POST /messages</code>
	 */
	public Response createMessage() {
		return Response.status(501)
				.entity("Not implemented: " + "createMessage()")
				.type(MediaType.TEXT_PLAIN_TYPE).build();
	}

	/**
	 * <p>
	 * Retrieve a message
	 * </p>
	 * <code>GET /messages/xsdsd</code>
	 */
	public Response getMessage() {
		return Response.status(501)
				.entity("Not implemented: " + "getMessage()")
				.type(MediaType.TEXT_PLAIN_TYPE).build();
	}

	/**
	 * <p>
	 * Retrieve all messages for user.
	 * </p>
	 * <code>GET /messages?username=dbateman</code>
	 */
	public Response findMessages() {
		return Response.status(501)
				.entity("Not implemented: " + "findMessages()")
				.type(MediaType.TEXT_PLAIN_TYPE).build();
	}

	/**
	 * <p>
	 * Delete a message.
	 * </p>
	 * <code>DELETE /messages/xsdsd</code>
	 */
	public Response deleteMessage() {
		return Response.status(501)
				.entity("Not implemented: " + "deleteMessage()")
				.type(MediaType.TEXT_PLAIN_TYPE).build();
	}

}
