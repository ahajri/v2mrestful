package com.ahajri.v2m.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahajri.v2m.domain.Sender;
import com.ahajri.v2m.repository.SenderRepository;

@Component
@Service
public class SenderService implements IService<Sender> {

	@Autowired
	SenderRepository senderRepository;

	@Override
	public Sender save(Sender domain) {
		return senderRepository.persist(domain);
	}

	@Override
	public long count() {
		return senderRepository.count();
	}

	@Override
	public List<Sender> findAll() {
		return senderRepository.findAll();
	}

	@Override
	public List<Sender> findAll(String sortFieldName, String sortOrder) {
		return senderRepository.findAll(sortFieldName, sortOrder);
	}

	@Override
	public Sender find(Long id) {
		return senderRepository.find(id);
	}

	@Override
	public List<Sender> findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder) {
		return senderRepository.findEntries(firstResult, maxResults,
				sortFieldName, sortOrder);
	}

	@Override
	public void remove(Sender domain) {
		senderRepository.remove(domain);
	}

	@Override
	public Sender merge(Sender domain) {
		return senderRepository.merge(domain);
	}

}
