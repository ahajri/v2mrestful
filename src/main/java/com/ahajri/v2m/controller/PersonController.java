package com.ahajri.v2m.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

import com.ahajri.v2m.domain.Person;
import com.ahajri.v2m.domain.json.adapter.PersonJsonAdapter;
import com.ahajri.v2m.service.PersonService;

@Controller
@RequestMapping("/people")
public class PersonController extends AController{
	
	PersonService personService;
	
	@Autowired
	PersonController(PersonService personService){
		this.personService=personService;
	}

	public PersonController(MessageSource msgSource,
			PersonService personServiceMock) {
		this.personService=personServiceMock;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Person person = personService.find(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        permitRemoteWScall(headers);
        if (person == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(PersonJsonAdapter.toJson(person), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        permitRemoteWScall(headers);
        List<Person> result = personService.findAll();
        return new ResponseEntity<String>(PersonJsonAdapter.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(value="/create",method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Person person = PersonJsonAdapter.fromJsonToPerson(json);
        personService.save(person);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        permitRemoteWScall(headers);
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+person.getId()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Person person: PersonJsonAdapter.fromJsonArrayToPersons(json)) {
            personService.save(person);
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
        Person person = PersonJsonAdapter.fromJsonToPerson(json);
        person.setId(id);
        if (personService.merge(person) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Person person = personService.find(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        permitRemoteWScall(headers);
        if (person == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        personService.remove(person);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
