package com.fernando.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.Entities.Insurance;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.InsuranceRepository;

@Service
public class InsuranceService {

	@Autowired
	InsuranceRepository repository;

	// FindAll
	public List<Insurance> findAll() {
		return repository.findAll();
	}

	// FindById
	public Insurance findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	// Create
	public Insurance create(Insurance insurance) {
		if (insurance == null)
			throw new RequiredObjectIsNullException();
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
		return repository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		Insurance entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
