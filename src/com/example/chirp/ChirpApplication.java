package com.example.chirp;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class ChirpApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(HelloResource.class);
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return super.getSingletons();
	}

}
