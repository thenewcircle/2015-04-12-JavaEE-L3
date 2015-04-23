package com.example.chirp.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ResourceNotImplementedException extends WebApplicationException {

	private static final long serialVersionUID = -5402670499690799363L;

	@Override
	public Response getResponse() {
		String method = "...";
		Response.status(501).type(MediaType.TEXT_PLAIN_TYPE)
				.entity("Not Implmented: " + method).build();
		return super.getResponse();
	}
	
	private String getCaller(int height) {
		StackTraceElement[] trace = this.getStackTrace();
		return null;
	}

}
