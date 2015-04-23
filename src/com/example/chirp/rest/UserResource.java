package com.example.chirp.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.example.chirp.model.User;
import com.example.chirp.rest.representations.UserDTO;
import com.example.chirp.rest.representations.UserListDTO;
import com.example.chirp.services.UserRepository;

@Path("/users")
public class UserResource {

	@EJB
	private UserRepository userRepository;

	/**
	 * <p>
	 * Create a user.
	 * </p>
	 * <code>PUT /users/dbateman</code>
	 */
	@Path("{userName}")
	@Produces({ "text/xml", "application/xml", "application/json" })
	@PUT
	public Response createUser(@PathParam("userName") String userName,
			String realName) {
		User user = userRepository.findOneOrNullByUserName(userName);
		if (user == null) {
			user = userRepository.create(userName, realName);
			URI uri = UriBuilder.fromPath("/users/{0}").build(userName);
			return Response.created(uri).entity(realName).build();
		} else {
			user.setRealName(realName);
			user = userRepository.createOrUpdate(user);
			UserDTO dto = new UserDTO(user, false);
			return Response.ok(dto).build();
		}
	}

	/**
	 * <p>
	 * Retrieve a user as plain text.
	 * </p>
	 * <code>GET /users/dbateman</code>
	 */
	@Path("{userName}")
	@Produces("text/plain")
	@GET
	public Response findUserAsText(@PathParam("userName") String userName) {
		User user = userRepository.findExactlyOneByUserName(userName);
		return Response.ok(user.getRealName()).build();
	}

	/**
	 * <p>
	 * Retrieve a user.
	 * </p>
	 * <code>GET /users/dbateman</code>
	 */
	@Path("{userName}")
	@Produces({ "text/xml", "application/xml", "application/json" })
	@GET
	public Response findUser(@PathParam("userName") String userName) {
		User user = userRepository.findExactlyOneByUserName(userName);
		UserDTO result = new UserDTO(user, false);
		return Response.ok(result).build();
	}

	/**
	 * <p>
	 * Query for users users.
	 * </p>
	 * <code>GET /users?realName={realName}</code>
	 */
	@GET
	@Produces({ "text/xml", "application/xml", "application/json" })
	public Response findUsers(@QueryParam("realName") String realName) {
		User example = new User();
		example.setRealName(realName);
		List<User> userList = userRepository.queryByExample(example, 0, 1000);
		UserListDTO dto = new UserListDTO(userList, false);
		return Response.ok(dto).build();
	}

	/**
	 * <p>
	 * Delete a user.
	 * </p>
	 * <code>DELETE /users/dbateman</code>
	 */
	@Path("{userName}")
	@DELETE
	public Response deleteUser(@PathParam("userName") String userName) {
		return Response.status(501)
				.entity("Not implemented: " + "deleteUser()")
				.type(MediaType.TEXT_PLAIN_TYPE).build();
	}

}
