package com.ahajri.v2m.domain.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 * Generic Repository Class, will be extended by all Repository Beans
 * 
 * @author Anis HAJRI
 * 
 * @param <T>
 */
public class GenericRepository<T> implements IGenericRepository<T> {

	private Class<T> type;

	private final List<String> fieldNames4OrderClauseFilter = java.util.Arrays
			.asList("");

	public GenericRepository() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@PersistenceContext
	transient EntityManager entityManager;

	public final EntityManager entityManager() {
		EntityManager em = new GenericRepository<T>().entityManager;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Override
	public long count() {
		final StringBuffer queryString = new StringBuffer(
				"SELECT count(o) from ");
		queryString.append(type.getSimpleName()).append(" o ");
		final Query query = this.entityManager().createQuery(
				queryString.toString());
		return (Long) query.getSingleResult();
	}

	@Override
	public List<T> findAll() {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(type.getSimpleName()).append(" o ");
		final Query query = this.entityManager().createQuery(
				queryString.toString());
		return query.getResultList();
	}

	@Override
	public List<T> findAll(String sortFieldName, String sortOrder) {
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
		return (List<T>) entityManager().createQuery(jpaQuery, type.getClass())
				.getResultList();
	}

	@Override
	public T find(Long id) {
		if (id == null)
			return null;
		return (T) this.entityManager().find(type.getClass(), id);
	}

	@Override
	public List<T> findEntries(int firstResult, int maxResults) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(type.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		return (List<T>) entityManager().createQuery(jpaQuery, type.getClass())
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Override
	public List<T> findEntries(int firstResult, int maxResults,
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
		return (List<T>) entityManager().createQuery(jpaQuery, type.getClass())
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Transactional
	@Override
	public T persist(T domain) {
		this.entityManager().persist(domain);
		return domain;
	}

	@Transactional
	@Override
	public void remove(T domain) {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		if (this.entityManager.contains(this)) {
			this.entityManager.remove(this);
		} else {
			T attached = find(getReflectedId(domain));
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
	public T merge(T domain) {
		 if (this.entityManager == null)
			this.entityManager = entityManager();
		return this.entityManager.merge(domain);
	}
	
	private Long getReflectedId(T domain) {
		Method getIdMethod;
		try {
			getIdMethod = domain.getClass().getDeclaredMethod("getId",
					null);
			Long id = (Long) getIdMethod.invoke(domain, null);
			return id;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
