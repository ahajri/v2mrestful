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

import com.ahajri.v2m.domain.Sender;
import com.ahajri.v2m.domain.json.adapter.SenderJsonAdapter;
import com.ahajri.v2m.service.SenderService;

@Controller
@RequestMapping("/senders")
public class SenderController extends AController{
	
	SenderService senderService;
	
	@Autowired
	public SenderController(SenderService senderService) {
		this.senderService=senderService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Sender sender = senderService.find(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        permitRemoteWScall(headers);
        if (sender == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(SenderJsonAdapter.toJson(sender), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        permitRemoteWScall(headers);
        List<Sender> result = senderService.findAll();
        return new ResponseEntity<String>(SenderJsonAdapter.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Sender sender = SenderJsonAdapter.fromJsonToSender(json);
        senderService.save(sender);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        permitRemoteWScall(headers);
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+sender.getId()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Sender sender: SenderJsonAdapter.fromJsonArrayToSenders(json)) {
            senderService.save(sender);
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
        Sender sender = SenderJsonAdapter.fromJsonToSender(json);
        sender.setId(id);
        if (senderService.merge(sender) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Sender sender = senderService.find(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        permitRemoteWScall(headers);
        if (sender == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        senderService.remove(sender);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
