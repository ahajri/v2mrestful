package com.ahajri.v2m.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.ahajri.v2m.domain.VoiceFile;
import com.ahajri.v2m.domain.json.adapter.VoiceFileJsonAdapter;
import com.ahajri.v2m.service.VoiceFileService;

@Controller
@RequestMapping("/voicefiles")
public class VoiceFileController extends AController {

	VoiceFileService voiceFileService;

	@Autowired
	public VoiceFileController(VoiceFileService voiceFileService) {
		this.voiceFileService = voiceFileService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
		VoiceFile voiceFile = voiceFileService.find(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		permitRemoteWScall(headers);
		if (voiceFile == null) {
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(VoiceFileJsonAdapter.toJson(voiceFile), headers,
				HttpStatus.OK);
	}

	@RequestMapping(headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> listJson() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		permitRemoteWScall(headers);
		List<VoiceFile> result = voiceFileService.findAll();
		return new ResponseEntity<String>(VoiceFileJsonAdapter.toJsonArray(result),
				headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> createFromJson(@RequestBody String json,
			UriComponentsBuilder uriBuilder) {
		VoiceFile voiceFile = VoiceFileJsonAdapter.fromJsonToVoiceFile(json);
		voiceFileService.save(voiceFile);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		permitRemoteWScall(headers);
		RequestMapping a = (RequestMapping) getClass().getAnnotation(
				RequestMapping.class);
		headers.add(
				"Location",
				uriBuilder
						.path(a.value()[0] + "/" + voiceFile.getId())
						.build().toUriString());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
		for (VoiceFile voiceFile : VoiceFileJsonAdapter.fromJsonArrayToVoiceFiles(json)) {
			voiceFileService.save(voiceFile);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		permitRemoteWScall(headers);
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateFromJson(@RequestBody String json,
			@PathVariable("id") Long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		permitRemoteWScall(headers);
		VoiceFile voiceFile = VoiceFileJsonAdapter.fromJsonToVoiceFile(json);
		voiceFile.setId(id);
		if (voiceFileService.merge(voiceFile) == null) {
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
		VoiceFile voiceFile = voiceFileService.find(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		permitRemoteWScall(headers);
		if (voiceFile == null) {
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		}
		voiceFileService.remove(voiceFile);
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}
}
