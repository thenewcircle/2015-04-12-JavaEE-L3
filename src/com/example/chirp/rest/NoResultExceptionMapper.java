package com.example.chirp.rest;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoResultExceptionMapper implements ExceptionMapper<NoResultException> {

	@Override
	public Response toResponse(NoResultException ex) {
		return Response.status(404).entity("Couldn't find that entity in database").build();
	}

}
