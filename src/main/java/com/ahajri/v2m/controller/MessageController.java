package com.ahajri.v2m.controller;

import java.util.List;

import org.apache.log4j.Logger;
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

import com.ahajri.v2m.domain.Message;
import com.ahajri.v2m.domain.json.adapter.MessageJsonAdapter;
import com.ahajri.v2m.service.MessageService;

@Controller
@RequestMapping("/messages")
public class MessageController extends AController {

	Logger logger = Logger.getLogger(MessageController.class);

	MessageService messageService;

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
		Message message = messageService.find(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		permitRemoteWScall(headers);
		if (message == null) {
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(MessageJsonAdapter.toJson(message),
				headers, HttpStatus.OK);
	}

	@RequestMapping(headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> listJson() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		permitRemoteWScall(headers);
		List<Message> result = messageService.findAll();
		String[] fields = { "id", "subject", "body" };
		return new ResponseEntity<String>(
				MessageJsonAdapter.toJsonArray(result), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> createFromJson(@RequestBody String json,
			UriComponentsBuilder uriBuilder) {
		Message message = MessageJsonAdapter.fromJsonToMessage(json);
		messageService.save(message);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		permitRemoteWScall(headers);
		RequestMapping a = (RequestMapping) getClass().getAnnotation(
				RequestMapping.class);
//		headers.add("Location",
//				uriBuilder.path(a.value()[0] + "/" + message.getId()).build()
//						.toUriString());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/create/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
		for (Message message : MessageJsonAdapter.fromJsonArrayToMessages(json)) {
			messageService.save(message);
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
		Message message = MessageJsonAdapter.fromJsonToMessage(json);
		message.setId(id);
		if (messageService.merge(message) == null) {
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
		Message message = messageService.find(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		permitRemoteWScall(headers);
		if (message == null) {
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		}
		messageService.remove(message);
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}
}
