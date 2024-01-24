package com.fernando.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fernando.Repositories.UserRepository;
import com.fernando.Security.AccountCredential;
import com.fernando.Security.Token;
import com.fernando.Security.Jwt.JwtTokenProvider;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredential data) {
		try {
			var userName = data.getUsername();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			var user = userRepository.findByUserName(userName);
			var tokenResponse = new Token();
			if (user != null) {
				tokenResponse = jwtTokenProvider.createAccessToken(userName, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + userName + "not found!");
			}
			return ResponseEntity.ok(tokenResponse);

		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}

	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String userName, String refreshToken) {
			var user = userRepository.findByUserName(userName);
			var tokenResponse = new Token();
			if (user != null) {
				tokenResponse = jwtTokenProvider.refreshToken(refreshToken);
			} else {
				throw new UsernameNotFoundException("Username " + userName + "not found!");
			}
			return ResponseEntity.ok(tokenResponse);

	}
}
