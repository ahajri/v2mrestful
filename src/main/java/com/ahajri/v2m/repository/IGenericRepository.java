package com.ahajri.v2m.repository;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
/**
 * Interface to be implemented by Repository classes
 * 
 * @author Anis HAJRI
 *
 * @param <T>: Generics of domain classes or Entities
 */
public interface IGenericRepository<T> {
	
	public long count();

	public List<T> findAll();

	public List<T> findAll(String sortFieldName, String sortOrder);

	public T find(Long id);

	public List<T> findEntries(int firstResult, int maxResults);

	public List<T> findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder);

	public T persist(T domain) throws ConstraintViolationException;

	public void remove(T domain);

	public void flush();

	public T merge(T domain);
	
	public void rollBack();

}
