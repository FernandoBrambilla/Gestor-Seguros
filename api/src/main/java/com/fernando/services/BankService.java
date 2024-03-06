package com.fernando.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.fernando.Controllers.BankController;
import com.fernando.Entities.Bank;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.BankRepository;

@Service
public class BankService {

	BankRepository repository;
	
	private final ModelMapper mapper;
	
	@Autowired
	public BankService(BankRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// FindAll
	public List<Bank> findAll() {
		return repository.findAll().stream()
				.map(bank -> mapper.map(bank, Bank.class))
				.collect(Collectors.toList());
	}

	// FindById
	public Bank findById(Integer id) {
		var entity = repository.findById(id);
		Bank bankVo = entity.map(bank -> mapper.map(bank, Bank.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		
		bankVo.add(linkTo(methodOn(BankController.class).findById(id)).withSelfRel());
		return bankVo;
		
	

		
	}

	// Create
	public Bank create(Bank bank) {
		if (bank == null)
			throw new RequiredObjectIsNullException();
		return repository.save(bank);
	}

	// Update
	public Bank update(Bank bank) {
		if (bank == null)
			throw new RequiredObjectIsNullException();
		Bank entity = repository.findById(bank.getId())
				.orElseThrow(() -> new ResourceNotFoundException());
		entity.setName(bank.getName());
		entity.setAg(bank.getAg());
		entity.setAccount(bank.getAccount());
		entity.setAccountBankType(bank.getAccountBankType());
		return repository.save(entity);
	}

	// Delete
	public void delete(Integer id) {
		Bank entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
