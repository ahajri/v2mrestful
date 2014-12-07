package com.ahajri.v2m.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahajri.v2m.domain.Message;
import com.ahajri.v2m.repository.MessageRepository;

@Component
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class MessageService implements IService<Message> {
	private final Logger log = LoggerFactory.getLogger(PersonService.class);

	
	MessageRepository messageRepository;
	
	@Autowired
	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public Message save(Message domain) {
		return messageRepository.persist(domain);
	}

	@Override
	public long count() {
		return messageRepository.count();
	}

	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

	@Override
	public List<Message> findAll(String sortFieldName, String sortOrder) {
		return messageRepository.findAll(sortFieldName, sortOrder);
	}

	@Override
	public Message find(Long id) {
		return messageRepository.find(id);
	}

	@Override
	public List<Message> findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder) {
		return messageRepository.findEntries(firstResult, maxResults,
				sortFieldName, sortOrder);
	}

	@Override
	public void remove(Message domain) {
		messageRepository.remove(domain);
	}

	@Override
	public Message merge(Message domain) {
		return messageRepository.merge(domain);
	}

}
