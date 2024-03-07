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
import com.fernando.Entities.Insurance;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.InsuranceRepository;

@Service
public class InsuranceService {

	InsuranceRepository repository;
	
	private final ModelMapper mapper;
	
	@Autowired
	public InsuranceService(InsuranceRepository repository,  ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// FindAll
	public List<Insurance> findAll() {
		var entity = repository.findAll().stream()
				.map(insurances -> mapper.map(insurances, Insurance.class))
				.collect(Collectors.toList());
			//LINK HATEOAS
			entity.stream().forEach(insurances -> insurances.add(linkTo(methodOn(InsuranceController.class)
				.findById(insurances.getId())).withSelfRel()));
			return entity;
	}

	// FindById
	public Insurance findById(Long id) {
		var entity = repository.findById(id);
		var insuranceVo = entity.map(insurance -> mapper.map(insurance, Insurance.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		//LINK HATEOAS
		insuranceVo.add(linkTo(methodOn(InsuranceController.class).findById(id)).withSelfRel());
		return insuranceVo;	
	}

	// Create
	public Insurance create(Insurance insurance) {
		if (insurance == null)
			throw new RequiredObjectIsNullException();
		var insuranceVo = repository.save(insurance);
		
		//LINK HATEOAS
		insuranceVo.add(linkTo(methodOn(InsuranceController.class).findById(insuranceVo.getId())).withSelfRel());
		return repository.save(insurance);
	}

	// Update
	public Insurance update(Insurance insurance) {
		if (insurance == null)
			throw new RequiredObjectIsNullException();
		Insurance entity = repository.findById(insurance.getId())
				.orElseThrow(() -> new ResourceNotFoundException());
		entity.setInsuranceType(insurance.getInsuranceType());
		entity.setValidity(insurance.getValidity());
		entity.setVehicle(insurance.getVehicle());
		entity.setFipe(insurance.getFipe());
		entity.setPlaca(insurance.getPlaca());
		entity.setChassi(insurance.getChassi());
		entity.setZeroKm(insurance.getZeroKm());
		entity.setYearMod(insurance.getYearMod());
		entity.setCategory(insurance.getCategory());
		entity.setProposalNumber(insurance.getProposalNumber());
		entity.setBonusClass(insurance.getBonusClass());
		entity.setFranchise(insurance.getFranchise());
		entity.setAmount(insurance.getAmount());
		//LINK HATEOAS
		entity.add(linkTo(methodOn(ClientPFController.class).findById(insurance.getId())).withSelfRel());
		return repository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		Insurance entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
