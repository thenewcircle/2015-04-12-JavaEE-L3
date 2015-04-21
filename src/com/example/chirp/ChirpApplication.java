package com.example.chirp;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.example.chirp.rest.UserResource;

@ApplicationPath("/api")
public class ChirpApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(HelloResource.class);
		classes.add(UserResource.class);
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return super.getSingletons();
	}

}
