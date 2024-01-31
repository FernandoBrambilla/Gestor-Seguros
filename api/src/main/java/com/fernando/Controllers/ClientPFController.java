package com.fernando.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.Entities.ClientPF;
import com.fernando.services.ClientPFService;

@RestController
@RequestMapping(value = "/clientspf")
public class ClientPFController {

	@Autowired
	private ClientPFService service;

	
	//FindAll Controller
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClientPF> findAll(){
		return service.findAll();
	}
	
	//FindByName Controller
	
	@GetMapping(value= "/name", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientPF>> findByName(@RequestParam String name){
		return new ResponseEntity<List<ClientPF>> (service.findByName(name),HttpStatus.OK);
	}
	
	//FindById Controller
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientPF findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	//Create Controller
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientPF create(@RequestBody ClientPF clientPF) {
		return service.create(clientPF);	
	}
	
	//Update Controller
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientPF update(@RequestBody ClientPF clientPF) {
		return service.update(clientPF);
	}
	
	//Delete Controller
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable (value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}