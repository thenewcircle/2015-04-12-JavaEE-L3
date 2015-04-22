package com.example.chirp.rest;

import java.net.URI;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.example.chirp.model.User;
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
	@Consumes("text/plain")
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
			userRepository.createOrUpdate(user);
			return Response.ok(realName).build();
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
	 * Retrieve a user
	 * </p>
	 * <code>GET /users/dbateman</code>
	 */
	@Path("{userName}")
	@Produces({ "text/xml", "application/xml", "application/json" })
	@GET
	public Response findUser(@PathParam("userName") String userName) {
		User user = userRepository.findExactlyOneByUserName(userName);
		return Response.ok(user).build();
	}

	/**
	 * <p>
	 * Retrieve all users.
	 * </p>
	 * <code>GET /users</code>
	 */

	/**
	 * <p>
	 * Delete a user.
	 * </p>
	 * <code>DELETE /users/dbateman</code>
	 */

}
