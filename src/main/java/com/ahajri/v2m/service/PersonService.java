package com.ahajri.v2m.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahajri.v2m.domain.Person;
import com.ahajri.v2m.repository.PersonRepository;

@Component
@Service
public class PersonService implements IService<Person> {

	private final Logger log = LoggerFactory.getLogger(PersonService.class);

	@Autowired
	PersonRepository personRepository;

	@Override
	public Person save(Person domain) {
		try {
			return personRepository.persist(domain);
		} catch (ConstraintViolationException e) {
			log.error("Constraint Violation due to: " + e.getMessage());
			personRepository.rollBack();
			return null;
		}
	}

	@Override
	public long count() {
		return personRepository.count();
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public List<Person> findAll(String sortFieldName, String sortOrder) {
		return personRepository.findAll(sortFieldName, sortOrder);
	}

	@Override
	public Person find(Long id) {
		return personRepository.find(id);
	}

	@Override
	public List<Person> findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder) {
		if (sortFieldName == null && sortOrder == null) {
			return personRepository.findEntries(firstResult, maxResults);
		} else {
			return personRepository.findEntries(firstResult, maxResults,
					sortFieldName, sortOrder);
		}
	}

	@Override
	public void remove(Person domain) {
		personRepository.remove(domain);

	}

	@Override
	public Person merge(Person domain) {
		return personRepository.merge(domain);
	}

	public void rollback() {
		personRepository.rollBack();
	}

}
