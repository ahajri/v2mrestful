package com.ahajri.v2m.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahajri.v2m.domain.Receiver;
import com.ahajri.v2m.repository.ReceiverRepository;

@Component
@Service
public class ReceiverService implements IService<Receiver> {

	@Autowired
	ReceiverRepository receiverRepository;

	@Override
	public Receiver save(Receiver domain) {
		return receiverRepository.persist(domain);
	}

	@Override
	public long count() {
		return receiverRepository.count();
	}

	@Override
	public List<Receiver> findAll() {
		return receiverRepository.findAll();
	}

	@Override
	public List<Receiver> findAll(String sortFieldName, String sortOrder) {
		return receiverRepository.findAll(sortFieldName, sortOrder);
	}

	@Override
	public Receiver find(Long id) {
		return receiverRepository.find(id);
	}

	@Override
	public List<Receiver> findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder) {
		return receiverRepository.findEntries(firstResult, maxResults,
				sortFieldName, sortOrder);
	}

	@Override
	public void remove(Receiver domain) {
		receiverRepository.remove(domain);
	}

	@Override
	public Receiver merge(Receiver domain) {
		return receiverRepository.merge(domain);
	}

}
