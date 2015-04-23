package com.example.chirp.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.example.chirp.model.Message;

@Stateless
public class MessageRepositorySessionBean implements MessageRepository {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	UserRepository userRepository;
	
	@Override
	public Message createMessage(Message message) {
		if (message.getId() != null) {
			// Wait, it's already got an ID?!?
			throw new EntityExistsException();
		}
		em.persist(message);
		return message;
	}

	@Override
	public Message findExactlyOne(String messageId) {
		Long id = Long.parseLong(messageId);
		Message message = em.find(Message.class, id);
		if (message == null) {
			throw new NoResultException();
		}
		return message;
	}

	@Override
	public List<Message> queryByExample(Message example) {
		return queryByExample(example, null, null);
	}

	@Override
	public List<Message> queryByExample(Message example, Integer offset,
			Integer limit) {
		Session hibernate = (Session) em.getDelegate();
		Criteria criteria = hibernate.createCriteria(Message.class).add(Example.create(example));
		if (offset != null)
			criteria.setFirstResult(offset);
		if (limit != null)
			criteria.setMaxResults(limit);
		@SuppressWarnings("unchecked")
		List<Message> results = criteria.list();
		return results;
	}

}
