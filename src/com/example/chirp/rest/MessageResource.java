package com.example.chirp.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.chirp.model.Message;

public class MessageResource {
	/**
	 * <p>
	 * A user creates a message.
	 * </p>
	 * <code>POST /messages</code>
	 */
	@POST
	public Response createMessage(Message message) {
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
	@GET
	@Path("{messageId}")
	public Response getMessage(@PathParam("messageId") String messageId) {
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
	@GET
	public Response findMessages(@QueryParam("username") String userName) {
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
	@DELETE
	@Path("{messageId}")
	public Response deleteMessage(@PathParam("messageId") String messageId) {
		return Response.status(501)
				.entity("Not implemented: " + "deleteMessage()")
				.type(MediaType.TEXT_PLAIN_TYPE).build();
	}

}
