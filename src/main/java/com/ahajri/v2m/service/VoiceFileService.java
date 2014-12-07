package com.ahajri.v2m.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahajri.v2m.domain.VoiceFile;
import com.ahajri.v2m.repository.VoiceFileRepository;

@Component
@Service
public class VoiceFileService implements IService<VoiceFile> {

	@Autowired
	VoiceFileRepository voiceFileRepository;

	@Override
	public VoiceFile save(VoiceFile  domain) {
		return voiceFileRepository.persist(domain);
	}

	@Override
	public long count() {
		return voiceFileRepository.count();
	}

	@Override
	public List<VoiceFile> findAll() {
		return voiceFileRepository.findAll();
	}

	@Override
	public List<VoiceFile> findAll(String sortFieldName, String sortOrder) {
		return voiceFileRepository.findAll(sortFieldName, sortOrder);
	}

	@Override
	public VoiceFile find(Long id) {
		return voiceFileRepository.find(id);
	}

	@Override
	public List<VoiceFile> findEntries(int firstResult, int maxResults,
			String sortFieldName, String sortOrder) {
		return voiceFileRepository.findEntries(firstResult, maxResults,
				sortFieldName, sortOrder);
	}

	@Override
	public void remove(VoiceFile  domain) {
		voiceFileRepository.remove(domain);
	}

	@Override
	public VoiceFile merge(VoiceFile  domain) {
		return voiceFileRepository.merge(domain);
	}

}
