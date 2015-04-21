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
	 * GET http://localhost:8080/chirp/api/users/{userName}
	 * Return body as plain text: Real Name
	 */
	@Path("{userName}")
	@Produces("text/plain")
	@GET
	@TransactionAttribute()
	public Response findUserAsText(@PathParam("userName") String userName) {
		User user = userRepository.findExactlyOneByUserName(userName);
		return Response.ok(user.getRealName()).build();
	}
	
	/**
	 * GET http://localhost:8080/chirp/api/users/{userName}
	 * Return user as xml or json
	 */
	@Path("{userName}")
	@Produces({"text/xml", "application/xml", "application/json"})
	@GET
	@TransactionAttribute()
	public Response findUser(@PathParam("userName") String userName) {
		User user = userRepository.findExactlyOneByUserName(userName);
		return Response.ok(user).build();
	}
	
	/**
	 * PUT http://localhost:8080/chirp/api/users/{userName}
	 * Upload body as plain text: Real Name
	 */
	@Path("{userName}")
	@Consumes("text/plain")
	@PUT
	public Response createUser(@PathParam("userName") String userName, String realName) {
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
	
}










