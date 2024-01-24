package com.fernando.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "insurances")
public class Insurance implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "insurance_type")
	private String insuranceType;
	private Date validity;
	private String vehicle;
	private String fipe;
	private String placa;
	private String chassi;
	private Boolean zeroKm;
	private String yearMod;
	private String category;
	
	@Column (name = "proposal_number")
	private Long proposalNumber;
	
	@Column (name = "bonus_class")
	private Integer bonusClass;
	
	private Double franchise;
	private Double amount;
		
	//CONSTRUCTOR
	public Insurance() {
	}

	//GETTERS AND SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getFipe() {
		return fipe;
	}

	public void setFipe(String fipe) {
		this.fipe = fipe;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public Boolean getZeroKm() {
		return zeroKm;
	}

	public void setZeroKm(Boolean zeroKm) {
		this.zeroKm = zeroKm;
	}

	public String getYearMod() {
		return yearMod;
	}

	public void setYearMod(String yearMod) {
		this.yearMod = yearMod;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(Long proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getBonusClass() {
		return bonusClass;
	}

	public void setBonusClass(Integer bonusClass) {
		this.bonusClass = bonusClass;
	}

	public Double getFranchise() {
		return franchise;
	}

	public void setFranchise(Double franchise) {
		this.franchise = franchise;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insurance other = (Insurance) obj;
		return Objects.equals(id, other.id);
	}
	
	
}


