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

import com.ahajri.v2m.domain.Receiver;
import com.ahajri.v2m.domain.json.adapter.ReceiverJsonAdapter;
import com.ahajri.v2m.service.ReceiverService;

@Controller
@RequestMapping("/receivers")
public class ReceiverController extends AController {
	
	ReceiverService receiverService;
	
	@Autowired
	public ReceiverController(ReceiverService receiverService) {
		this.receiverService=receiverService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Receiver receiver = receiverService.find(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        permitRemoteWScall(headers);
        if (receiver == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(ReceiverJsonAdapter.toJson(receiver), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        permitRemoteWScall(headers);
        List<Receiver> result = receiverService.findAll();
        return new ResponseEntity<String>(ReceiverJsonAdapter.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Receiver receiver = ReceiverJsonAdapter.fromJsonToReceiver(json);
        receiverService.save(receiver);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        permitRemoteWScall(headers);
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+receiver.getId()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Receiver receiver: ReceiverJsonAdapter.fromJsonArrayToReceivers(json)) {
            receiverService.save(receiver);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        permitRemoteWScall(headers);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        permitRemoteWScall(headers);
        Receiver receiver = ReceiverJsonAdapter.fromJsonToReceiver(json);
        receiver.setId(id);
        if (receiverService.merge(receiver) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Receiver receiver = receiverService.find(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        permitRemoteWScall(headers);
        if (receiver == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        receiverService.remove(receiver);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
