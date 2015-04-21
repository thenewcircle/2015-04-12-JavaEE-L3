package com.example.chirp;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloResource {

	@GET
	@Produces("text/html")
	public String sayHello() {
		return "<h1>Hello World</h1>";
	}

	/**
	 * GET http://localhost:8080/Chirp/api/hello/Doug
	 */
	@GET
	@Produces("text/html")
	@Path("{name}")
	public String sayHello(@PathParam("name") String name) {
		return String.format("<h1>Hello %s</h1>%n", name);
	}
	
	/**
	 * POST http://localhost:8080/Chirp/api/hello
	 * Doug
	 */
	@POST
	@Consumes("text/plain")
	@Produces("text/html")
	public String postHello(String name) {
		return String.format("<h1>Hello %s</h1>%n", name);
	}

	/**
	 * POST http://localhost:8080/Chirp/api/hello
	 * Doug
	 */
	@POST
	@Consumes("text/plain")
	@Produces("text/plain")
	public String postHelloAsText(String name) {
		return String.format("Hello %s", name);
	}

	/**
	 * POST http://localhost:8080/Chirp/api/hello
	 * Doug
	 */
	@POST
	@Consumes("x-training/weird")
	@Produces("x-training/weirder")
	public String postHelloAsWeird(String name) {
		return String.format("Goodbye %s", name);
	}

}













