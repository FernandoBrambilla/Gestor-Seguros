package com.fernando.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.Entities.ClientPF;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.ClientPFRepository;

@Service
public class ClientPFService {

	@Autowired
	ClientPFRepository repository;

	// FindAll
	public List<ClientPF> findAll() {
		return repository.findAll();
	}

	// FindById
	public ClientPF findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	// Create
	public ClientPF create(ClientPF clientPF) {
		if (clientPF == null)
			throw new RequiredObjectIsNullException();
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
		return repository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		ClientPF entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);

	}
}