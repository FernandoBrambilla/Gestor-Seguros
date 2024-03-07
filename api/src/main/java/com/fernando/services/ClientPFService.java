package com.fernando.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.Controllers.ClientPFController;
import com.fernando.Controllers.InsuranceController;
import com.fernando.Entities.ClientPF;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.ClientPFRepository;

@Service
public class ClientPFService {

	ClientPFRepository repository;
	
	private final ModelMapper mapper;
	
	
	@Autowired
	public ClientPFService(ClientPFRepository repository,  ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// FindAll
	public List<ClientPF> findAll() {
		var entity = repository.findAll().stream()
				.map(clients -> mapper.map(clients, ClientPF.class))
				.collect(Collectors.toList());
		//LINK HATEOAS
		entity.stream().forEach(clients -> clients.add(linkTo(methodOn(ClientPFController.class)
				.findById(clients.getId())).withSelfRel()));
		return entity;
	}

	//FindByName
	public List<ClientPF> findByName(String name){
		var entity = repository.findByName(name).stream()
				.map(client -> mapper.map(client, ClientPF.class))
				.collect(Collectors.toList());
		//LINK HATEOAS
		entity.stream().forEach(client -> client.add(linkTo(methodOn(ClientPFController.class)
				.findById(client.getId())).withSelfRel()));
		return entity;
	}

	// FindById
	public ClientPF findById(Long id) {
		var entity = repository.findById(id);
		var clientPFVo = entity.map(client -> mapper.map(client, ClientPF.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		//LINK HATEOAS
		clientPFVo.add(linkTo(methodOn(ClientPFController.class).findById(id)).withSelfRel());
		return clientPFVo;	
	}	

	// Create
	public ClientPF create(ClientPF clientPF) {
		if (clientPF == null)
			throw new RequiredObjectIsNullException();
		var insuranceVo = repository.save(clientPF);
		
		//LINK HATEOAS
		insuranceVo.add(linkTo(methodOn(ClientPFController.class).findById(insuranceVo.getId())).withSelfRel());
		return repository.save(clientPF);
	}

	// Update
	public ClientPF update(ClientPF clientPF) {
		if (clientPF == null)
			throw new RequiredObjectIsNullException();
		
		ClientPF entity = repository.findById(clientPF.getId())
				.orElseThrow(() -> new ResourceNotFoundException());
		entity.setName(clientPF.getName());
		entity.setPhone(clientPF.getPhone());
		entity.setEmail(clientPF.getEmail());
		entity.setCpf(clientPF.getCpf());
		entity.setRg(clientPF.getRg());
		entity.setDateNasc(clientPF.getDateNasc());
		entity.setDateExp(clientPF.getDateExp());
		entity.setAddress(clientPF.getAddress());
		entity.setAddressNumber(clientPF.getAddressNumber());
		entity.setAddressComplement(clientPF.getAddressComplement());
		entity.setCity(clientPF.getCity());
		entity.setUf(clientPF.getUf());
		entity.setCep(clientPF.getCep());
		entity.setBank(clientPF.getBank());
		entity.setInsurance(clientPF.getInsurance());
		//LINK HATEOAS
		entity.add(linkTo(methodOn(ClientPFController.class).findById(clientPF.getId())).withSelfRel());
		return repository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		ClientPF entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);

	}
}