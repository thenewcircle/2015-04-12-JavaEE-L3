package com.example.chirp.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

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
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public User findExactlyOne(Long id) throws NoResultException {
		User user = em.find(User.class, id);
		return user;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public User findOneOrNull(Long id) {
		try {
			return findExactlyOne(id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public User findExactlyOneByUserName(String userName) throws NoResultException {
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
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public User findOneOrNullByUserName(String userName) {
		try {
			return findExactlyOneByUserName(userName);
		} catch (NoResultException e) {
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

	@Override
	public List<User> queryByExample(User example, Integer offset, Integer limit) {
		Session hibernate = (Session) em.getDelegate();
		Criteria criteria = hibernate.createCriteria(User.class).add(Example.create(example));
		if (offset != null)
			criteria.setFirstResult(offset);
		if (limit != null)
			criteria.setMaxResults(limit);
		@SuppressWarnings("unchecked")
		List<User> result = criteria.list();
		return result;
	}

}
