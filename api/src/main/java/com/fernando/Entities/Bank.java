package com.fernando.Entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
@JsonPropertyOrder({"id", "name", "account_bank_type", "ag", "account"})
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column (nullable = false)
	private String name;
	
	@Column (name= ("account_bank_type"), nullable = false)
	private String accountBankType;
	
	@Column (nullable = false)
	private String ag;
	
	@Column (nullable = false)
	private String account;

	//CONSTUCTOR
	public Bank(Integer id, String name, String accountBankType, String ag,
			String account) {
		this.id = id;
		this.name = name;
		this.accountBankType = accountBankType;
		this.ag = ag;
		this.account = account;
	}

	public Bank() {
	
	}

	//GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountBankType() {
		return accountBankType;
	}

	public void setAccountBankType(String accountBankType) {
		this.accountBankType = accountBankType;
	}

	public String getAg() {
		return ag;
	}

	public void setAg(String ag) {
		this.ag = ag;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	//HASHCODE AND EQUALS
	@Override
	public int hashCode() {
		return Objects.hash(account, accountBankType, ag, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		return Objects.equals(account, other.account) && Objects.equals(accountBankType, other.accountBankType)
				&& Objects.equals(ag, other.ag) && Objects.equals(name, other.name);
	}	
	
}

