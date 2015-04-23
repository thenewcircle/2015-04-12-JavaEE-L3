package com.example.chirp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.chirp.model.Message;
import com.example.chirp.model.User;
import com.example.chirp.rest.representations.MessageDTO;
import com.example.chirp.rest.representations.MessageListDTO;
import com.example.chirp.services.MessageRepository;
import com.example.chirp.services.UserRepository;

@Path("messages")
public class MessageResource {

	@Inject  // Same as @EJB for our purposes.
	private MessageRepository messageRepository;

	@Inject
	private UserRepository userRepository;
	
	/**
	 * <p>
	 * A user creates a message.
	 * </p>
	 * <code>POST /messages</code>
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createMessage(MessageDTO messageDTO) {
		String userName = messageDTO.getUser().getUserName();
		User user = userRepository.findExactlyOneByUserName(userName);
		Message message = messageDTO.toEntity();
		message.setUser(user);
		message = messageRepository.createMessage(message);
		messageDTO = new MessageDTO(message);
		return Response.ok(messageDTO).build();
	}

	/**
	 * <p>
	 * Retrieve a message
	 * </p>
	 * <code>GET /messages/xsdsd</code>
	 */
	@GET
	@Path("{messageId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getMessage(@PathParam("messageId") String messageId) {
		Message message = messageRepository.findExactlyOne(messageId);
		MessageDTO dto = new MessageDTO(message);
		return Response.ok(dto).build();
	}

	/**
	 * <p>
	 * Retrieve all messages for user.
	 * </p>
	 * <code>GET /messages?username=dbateman</code>
	 */
	@GET
	public Response findMessages(@QueryParam("userName") String userName,
			@QueryParam("contains") String text, @QueryParam("offset") Integer offset,
			@QueryParam("limit") Integer limit) {
		Message example = new Message();
		User exampleUser = new User();
		exampleUser.setUserName(userName);
		example.setUser(exampleUser);
		example.setText(text);
		List<Message> messages = messageRepository.queryByExample(example, offset, limit);
		MessageListDTO dto = new MessageListDTO(messages);
		return Response.ok(dto).build();
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
