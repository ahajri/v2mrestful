package com.ahajri.v2m.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahajri.v2m.domain.Message;
import com.ahajri.v2m.domain.Receiver;

@Repository("messageRepository")
@Component
@Qualifier("messageRepository")
@Transactional
public class MessageRepository implements IGenericRepository<Message> {

	private final List<String> fieldNames4OrderClauseFilter = java.util.Arrays
			.asList("");

	public transient EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public final EntityManager entityManager() {
		EntityManager em = new GenericRepository<Receiver>().entityManager;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDSender aspects library?)");
		return em;
	}

	@Override
	public long count() {
		final StringBuffer queryString = new StringBuffer(
				"SELECT count(o) from ");
		queryString.append(Message.class.getSimpleName()).append(" o ");
		final Query query = this.entityManager.createQuery(queryString
				.toString());
		return (Long) query.getSingleResult();
	}

	@Override
	public List<Message>findAll() {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(Message.class.getSimpleName()).append(" o ");
		final Query query = this.entityManager.createQuery(queryString
				.toString());
		return query.getResultList();
	}

	@Override
	public List<Message>findAll(String sortFieldName, String sortOrder) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(Message.class.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
			if ("ASC".equalsIgnoreCase(sortOrder)
					|| "DESC".equalsIgnoreCase(sortOrder)) {
				jpaQuery = jpaQuery + " " + sortOrder;
			}
		}
		return (List<Message>) entityManager.createQuery(jpaQuery, Message.class)
				.getResultList();
	}

	@Override
	public Message find(Long id) {
		if (id == null)
			return null;
		return (Message) this.entityManager.find(Message.class, id);
	}

	@Override
	public List<Message>findEntries(int firstResult, int maxResults) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(Message.class.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		return (List<Message>) entityManager.createQuery(jpaQuery, Message.class)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Override
	public List<Message>findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(Message.class.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
			if ("ASC".equalsIgnoreCase(sortOrder)
					|| "DESC".equalsIgnoreCase(sortOrder)) {
				jpaQuery = jpaQuery + " " + sortOrder;
			}
		}
		return (List<Message>) entityManager.createQuery(jpaQuery, Message.class)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Override
	public Message persist(Message domain) {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.persist(domain);
		return domain;
	}

	@Transactional
	@Override
	public void remove(Message domain) {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		if (this.entityManager.contains(domain)) {
			this.entityManager.remove(domain);
		} else {
			Message attached = find(domain.getId());
			this.entityManager.remove(attached);
		}
	}

	@Transactional
	@Override
	public void flush() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.flush();
	}

	@Transactional
	@Override
	public Message merge(Message domain) {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		return this.entityManager.merge(domain);
	}

	@Override
	public void rollBack() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		entityManager.getTransaction().rollback();

	}

}
