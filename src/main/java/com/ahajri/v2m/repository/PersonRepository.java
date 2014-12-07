package com.ahajri.v2m.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ahajri.v2m.domain.Person;

@Repository("personRepository")
@Component
@Qualifier("personRepository")
public class PersonRepository implements IGenericRepository<Person>

{

	private final Class<Person> type = Person.class;

	private final List<String> fieldNames4OrderClauseFilter = java.util.Arrays
			.asList("");

	@PersistenceContext
	public transient EntityManager entityManager;

	public final EntityManager entityManager() {
		EntityManager em = new GenericRepository<Person>().entityManager;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	@Override
	public Person persist(Person domain) throws ConstraintViolationException {
		entityManager.persist(domain);
		return domain;
	}

	@Override
	public long count() {
		final StringBuffer queryString = new StringBuffer(
				"SELECT count(o) from ");
		queryString.append(type.getSimpleName()).append(" o ");
		final Query query = this.entityManager.createQuery(
				queryString.toString());
		return (Long) query.getSingleResult();
	}

	@Transactional
	@Override
	public List<Person> findAll() {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(type.getSimpleName()).append(" o ");
		final Query query = this.entityManager.createQuery(
				queryString.toString());
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Person> findAll(String sortFieldName, String sortOrder) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(type.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
			if ("ASC".equalsIgnoreCase(sortOrder)
					|| "DESC".equalsIgnoreCase(sortOrder)) {
				jpaQuery = jpaQuery + " " + sortOrder;
			}
		}
		return (List<Person>) entityManager.createQuery(jpaQuery,
				Person.class).getResultList();
	}

	@Override
	public Person find(Long id) {
		if (id == null)
			return null;
		return (Person) this.entityManager.find(Person.class, id);
	}

	@Transactional
	@Override
	public List<Person> findEntries(int firstResult, int maxResults) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(type.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		return (List<Person>) entityManager
				.createQuery(jpaQuery, Person.class)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Transactional
	@Override
	public List<Person> findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(type.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
			if ("ASC".equalsIgnoreCase(sortOrder)
					|| "DESC".equalsIgnoreCase(sortOrder)) {
				jpaQuery = jpaQuery + " " + sortOrder;
			}
		}
		return (List<Person>) entityManager
				.createQuery(jpaQuery, Person.class)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Transactional
	@Override
	public void remove(Person domain) {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		if (this.entityManager.contains(domain)) {
			this.entityManager.remove(domain);
		} else {
			Person attached = find(domain.getId());
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
	public Person merge(Person domain) {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		return this.entityManager.merge(domain);
	}

	@Transactional
	@Override
	public void rollBack() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		entityManager.getTransaction().rollback();
	}

}
