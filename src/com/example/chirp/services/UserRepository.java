package com.example.chirp.services;

import javax.persistence.EntityNotFoundException;

import com.example.chirp.model.User;

public interface UserRepository {

	/**
	 * Creates a new user in the database. The database will assign it a unique
	 * id.
	 * 
	 * @return a new user, with the id already assigned by the database.
	 */
	public User create(String userName, String realName);

	public User findOneOrNull(Long id);
	
	public User findExactlyOne(Long id) throws EntityNotFoundException;
	
	public User findOneOrNull(String userName);
	
	public User findExactlyOne(String userName) throws EntityNotFoundException;
	
	public User createOrUpdate(User entity);

	public void delete(Long id);
	
	public void delete(String userName);
	
}
