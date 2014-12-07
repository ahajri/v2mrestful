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
import com.ahajri.v2m.domain.VoiceFile;

@Repository("voiceFileRepository")
@Component
@Qualifier("voiceFileRepository")
public class VoiceFileRepository implements IGenericRepository<VoiceFile> {

	private final List<String> fieldNames4OrderClauseFilter = java.util.Arrays
			.asList("");

	public transient EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public final EntityManager entityManager() {
		EntityManager em = new GenericRepository<VoiceFile>().entityManager;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDSender aspects library?)");
		return em;
	}

	@Override
	public long count() {
		final StringBuffer queryString = new StringBuffer(
				"SELECT count(o) from ");
		queryString.append(VoiceFile.class.getSimpleName()).append(" o ");
		final Query query = this.entityManager.createQuery(queryString
				.toString());
		return (Long) query.getSingleResult();
	}

	@Override
	public List<VoiceFile> findAll() {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(VoiceFile.class.getSimpleName()).append(" o ");
		final Query query = this.entityManager.createQuery(queryString
				.toString());
		return query.getResultList();
	}

	@Override
	public List<VoiceFile> findAll(String sortFieldName, String sortOrder) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(VoiceFile.class.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
			if ("ASC".equalsIgnoreCase(sortOrder)
					|| "DESC".equalsIgnoreCase(sortOrder)) {
				jpaQuery = jpaQuery + " " + sortOrder;
			}
		}
		return (List<VoiceFile>) entityManager.createQuery(jpaQuery, VoiceFile.class)
				.getResultList();
	}

	@Override
	public VoiceFile find(Long id) {
		if (id == null)
			return null;
		return (VoiceFile) this.entityManager.find(VoiceFile.class, id);
	}

	@Override
	public List<VoiceFile> findEntries(int firstResult, int maxResults) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(VoiceFile.class.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		return (List<VoiceFile>) entityManager.createQuery(jpaQuery, VoiceFile.class)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Override
	public List<VoiceFile> findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder) {
		final StringBuffer queryString = new StringBuffer("SELECT o from ");
		queryString.append(VoiceFile.class.getSimpleName()).append(" o ");
		String jpaQuery = queryString.toString();
		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
			if ("ASC".equalsIgnoreCase(sortOrder)
					|| "DESC".equalsIgnoreCase(sortOrder)) {
				jpaQuery = jpaQuery + " " + sortOrder;
			}
		}
		return (List<VoiceFile>) entityManager.createQuery(jpaQuery, VoiceFile.class)
				.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	@Transactional
	@Override
	public VoiceFile persist(VoiceFile domain) {
		this.entityManager.persist(domain);
		return domain;
	}

	@Transactional
	@Override
	public void remove(VoiceFile domain) {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		if (this.entityManager.contains(domain)) {
			this.entityManager.remove(domain);
		} else {
			VoiceFile attached = find(domain.getId());
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
	public VoiceFile merge(VoiceFile domain) {
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
