package com.ahajri.v2m.service;

import java.util.List;

public interface IService<T> {
	
	public T save(T domain);
	public long count();
	public List<T> findAll();
	public List<T> findAll(String sortFieldName, String sortOrder);
	public T find(Long id);
	public List<T> findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder);
	public void remove(T domain);
	public T merge(T domain);
}
