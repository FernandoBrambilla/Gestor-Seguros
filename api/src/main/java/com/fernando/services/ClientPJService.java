package com.fernando.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.Entities.ClientPJ;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.ClientPJRepository;

@Service
public class ClientPJService {

	@Autowired
	ClientPJRepository repository;
	
	// FindAll
	public List<ClientPJ> findAll() {
		return repository.findAll();
	}

	// FindById
	public ClientPJ findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	// Create
	public ClientPJ create(ClientPJ clientPJ) {
		if (clientPJ == null)
			throw new RequiredObjectIsNullException();
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
		return repository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		ClientPJ entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);

	}
}