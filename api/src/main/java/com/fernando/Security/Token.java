package com.fernando.Security;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Token implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private Boolean authenticated;
	private Date create;
	private Date expiration;
	private String accessToken;
	private String refreshToken;
	
	//CONSTUCTOR
	public Token(String userName, Boolean authenticated, Date create, Date expiration, String accessToken,
			String refreshToken) {
		super();
		this.userName = userName;
		this.authenticated = authenticated;
		this.create = create;
		this.expiration = expiration;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public Token() {

	}

	//GETTERS AND SETTERS
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefleshToken() {
		return refreshToken;
	}

	public void setRefleshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	//HASHCODE AND EQUALS
	@Override
	public int hashCode() {
		return Objects.hash(accessToken, authenticated, create, expiration, refreshToken, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		return Objects.equals(accessToken, other.accessToken) && Objects.equals(authenticated, other.authenticated)
				&& Objects.equals(create, other.create) && Objects.equals(expiration, other.expiration)
				&& Objects.equals(refreshToken, other.refreshToken) && Objects.equals(userName, other.userName);
	}
}
