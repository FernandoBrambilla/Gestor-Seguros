package com.fernando.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity 
@Table(name = "client_pf")
public class ClientPF implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	
	@Column (nullable = false)
	private String name;
	
	@Column (nullable = false)
	private String phone;
	
	@Column (nullable = false)
	private String email;
	
	@Column (nullable = false, unique = true)
	private String cpf;
	
	@Column (nullable = false)
	private String rg;
	
	@Column (nullable = false, name = "date_nasc")
	@Temporal(TemporalType.DATE)
	private Date dateNasc;
	
	@Column (nullable = false, name = "date_exp")
	@Temporal(TemporalType.DATE)
	private Date dateExp;
	
	@Column (nullable = false)
	private String address;
	
	@Column (nullable = false, name = "address_number")
	private String addressNumber;
	
	@Column (name = "address_complement")
	private String addressComplement;
	
	@Column (nullable = false)
	private String city;
	
	@Column (nullable = false)
	private String uf;
	
	@Column (nullable = false)
	private String cep;
		
	@ManyToOne
	private Bank bank;
	
	@OneToMany (fetch = FetchType.EAGER)
	private List<Insurance> insurance;
	
	//CONSTUCTOR
	public ClientPF() {
			
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public Date getDateNasc() {
		return dateNasc;
	}

	public Date getDateExp() {
		return dateExp;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	public String getCity() {
		return city;
	}

	public String getUf() {
		return uf;
	}

	public String getCep() {
		return cep;
	}

	public Bank getBank() {
		return bank;
	}

	public List<Insurance> getInsurance() {
		return insurance;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setDateNasc(Date dateNasc) {
		this.dateNasc = dateNasc;
	}

	public void setDateExp(Date dateExp) {
		this.dateExp = dateExp;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public void setInsurance(List<Insurance> insurance) {
		this.insurance = insurance;
	}

	
	
	

}