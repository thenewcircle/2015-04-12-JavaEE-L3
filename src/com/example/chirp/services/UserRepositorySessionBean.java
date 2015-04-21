package com.example.chirp.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import com.example.chirp.model.User;

@Stateless
public class UserRepositorySessionBean implements UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User create(String userName, String realName) {
		User user = new User();
		user.setUserName(userName);
		user.setRealName(realName);
		em.persist(user);
		return user;
	}

	@Override
	public User findExactlyOne(Long id) throws EntityNotFoundException {
		User user = em.find(User.class, id);
		return user;
	}

	@Override
	public User findOneOrNull(Long id) {
		try {
			return findExactlyOne(id);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public User findExactlyOneByUserName(String userName) throws EntityNotFoundException {
		String queryString = "from User u where u.userName=:user";
		TypedQuery<User> query = em.createQuery(queryString, User.class);
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> u = criteria.from(User.class);
		criteria.where(builder.equal(u.get("userName"), userName));
		
		query.setParameter("user", userName);
		User user = query.getSingleResult();
		return user;
	}

	@Override
	public User findOneOrNullByUserName(String userName) {
		try {
			return findExactlyOneByUserName(userName);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public User createOrUpdate(User entity) {
		Long id = entity.getId();
		if (id == null) {
			em.persist(entity);
		} else {
			entity = em.merge(entity);
		}
		return entity;
	}

	@Override
	public void delete(Long id) {
		User user = findExactlyOne(id);
		em.remove(user);
	}

	@Override
	public void delete(String userName) {
		User user = findExactlyOneByUserName(userName);
		em.remove(user);
	}

}

