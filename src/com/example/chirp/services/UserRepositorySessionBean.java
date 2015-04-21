package com.example.chirp.services;

import javax.persistence.EntityNotFoundException;

import com.example.chirp.model.User;

public class UserRepositorySessionBean implements UserRepository {

	@Override
	public User create(String userName, String realName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOneOrNull(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findExactlyOne(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOneOrNull(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findExactlyOne(String userName) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createOrUpdate(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String userName) {
		// TODO Auto-generated method stub
		
	}

}
