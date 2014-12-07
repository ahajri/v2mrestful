package com.ahajri.v2m.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ahajri.v2m.domain.Sender;

@Component
@Repository("senderRepository")
@Qualifier("senderRepository")
public class SenderRepository implements IGenericRepository<Sender> {

	private final List<String> fieldNames4OrderClauseFilter = java.util.Arrays
			.asList("");

	public transient EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public final EntityManager entityManager() {
		EntityManager em = new GenericRepository<Sender>().entityManager;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDSender aspects library?)");
		return em;
	}

	@Override
	public long count() {
		final StringBuffer queryString = new StringBuffer(
				"SELECT count(o) from ");
		queryString.append(Sender.class.getSimpleName()).append(" o ");
		final Query query = this.entityManager.createQuery(queryString
				.toString());
		return (Long) query.getSingleResult();
	}

	@Override
	public List<Sender> findAll() {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(Sender.class.getSimpleName()).append(" o ");
		final Query query = this.entityManager.createQuery(queryString
				.toString());
		return query.getResultList();
	}

	@Override
	public List<Sender> findAll(String sortFieldName, String sortOrder) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(Sender.class.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
			if ("ASC".equalsIgnoreCase(sortOrder)
					|| "DESC".equalsIgnoreCase(sortOrder)) {
				jpaQuery = jpaQuery + " " + sortOrder;
			}
		}
		return (List<Sender>) entityManager.createQuery(jpaQuery, Sender.class)
				.getResultList();
	}

	@Override
	public Sender find(Long id) {
		if (id == null)
			return null;
		return (Sender) this.entityManager.find(Sender.class, id);
	}

	@Override
	public List<Sender> findEntries(int firstResult, int maxResults) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(Sender.class.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		return (List<Sender>) entityManager.createQuery(jpaQuery, Sender.class)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Override
	public List<Sender> findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(Sender.class.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
			if ("ASC".equalsIgnoreCase(sortOrder)
					|| "DESC".equalsIgnoreCase(sortOrder)) {
				jpaQuery = jpaQuery + " " + sortOrder;
			}
		}
		return (List<Sender>) entityManager.createQuery(jpaQuery, Sender.class)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Transactional
	@Override
	public Sender persist(Sender domain) {
		this.entityManager.persist(domain);
		return domain;
	}

	@Transactional
	@Override
	public void remove(Sender domain) {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		if (this.entityManager.contains(domain)) {
			this.entityManager.remove(domain);
		} else {
			Sender attached = find(domain.getId());
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
	public Sender merge(Sender domain) {
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
