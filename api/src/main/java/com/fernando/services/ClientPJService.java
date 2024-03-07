package com.fernando.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.Controllers.ClientPFController;
import com.fernando.Controllers.ClientPJController;
import com.fernando.Controllers.InsuranceController;
import com.fernando.Entities.ClientPJ;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.ClientPJRepository;

@Service
public class ClientPJService {
	
	ClientPJRepository repository;
	
	private final ModelMapper mapper;
		
	@Autowired
	public ClientPJService(ClientPJRepository repository,  ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	// FindAll
	public List<ClientPJ> findAll() {
		var entity = repository.findAll().stream()
			.map(clients -> mapper.map(clients, ClientPJ.class))
			.collect(Collectors.toList());
		//LINK HATEOAS
		entity.stream().forEach(clients -> clients.add(linkTo(methodOn(ClientPJController.class)
			.findById(clients.getId())).withSelfRel()));
		return entity;
	}
	
	//FindByName
	public List<ClientPJ> findByName(String name){
		var entity = repository.findByName(name).stream()
			.map(client -> mapper.map(client, ClientPJ.class))
			.collect(Collectors.toList());
		//LINK HATEOAS
		entity.stream().forEach(client -> client.add(linkTo(methodOn(ClientPJController.class)
			.findById(client.getId())).withSelfRel()));
		return entity;
	}

	// FindById
	public ClientPJ findById(Long id) {
		var entity = repository.findById(id);
		var clientPJVo = entity.map(client -> mapper.map(client, ClientPJ.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		//LINK HATEOAS
		clientPJVo.add(linkTo(methodOn(ClientPJController.class).findById(id)).withSelfRel());
		return clientPJVo;			
	}

	// Create
	public ClientPJ create(ClientPJ clientPJ) {
		if (clientPJ == null)
			throw new RequiredObjectIsNullException();
		var insuranceVo = repository.save(clientPJ);
		
		//LINK HATEOAS
		insuranceVo.add(linkTo(methodOn(ClientPJController.class).findById(insuranceVo.getId())).withSelfRel());
		return repository.save(clientPJ);
	}

	// Update
	public ClientPJ update(ClientPJ clientPJ) {
		if (clientPJ == null)
			throw new RequiredObjectIsNullException();
		var entity = repository.findById(clientPJ.getId())
				.orElseThrow(() -> new ResourceNotFoundException());
		entity.setName(clientPJ.getName());
		entity.setPhone(clientPJ.getPhone());
		entity.setEmail(clientPJ.getEmail());
		entity.setCnpj(clientPJ.getCnpj());
		entity.setIe(clientPJ.getIe());
		entity.setAddress(clientPJ.getAddress());
		entity.setAddressNumber(clientPJ.getAddressNumber());
		entity.setAddressComplement(clientPJ.getAddressComplement());
		entity.setCity(clientPJ.getCity());
		entity.setUf(clientPJ.getUf());
		entity.setCep(clientPJ.getCep());
		entity.setBank(clientPJ.getBank());
		entity.setInsurance(clientPJ.getInsurance());
		//LINK HATEOAS
		entity.add(linkTo(methodOn(ClientPFController.class).findById(clientPJ.getId())).withSelfRel());
		return repository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		ClientPJ entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);

	}
}