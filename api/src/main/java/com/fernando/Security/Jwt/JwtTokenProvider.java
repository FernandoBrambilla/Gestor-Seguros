package com.fernando.Security.Jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fernando.Exceptions.InvalidJwtAuthenticationException;
import com.fernando.Security.Token;
import com.fernando.services.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";
	
	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 10800000; //3 hour
	
	@Autowired
	private UserService userService;
	
	Algorithm algorithm = null;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}
	
	public Token createAccessToken(String userName, List<String> roles) {
		Date  now = new Date();
		Date vality = new Date(now.getTime() + validityInMilliseconds);
		var accessToken = getAccessToken(userName, roles, now, vality);
		var refreshToken = getRefreshToken(userName, roles, now);
		return new Token(userName, true, now, vality, accessToken, refreshToken);
	}
	
	public Token refreshToken(String refreshToken) {
		if(refreshToken.contains("Bearer ")) {
			refreshToken = refreshToken.substring("Bearer ".length());
		}
		JWTVerifier verifier = JWT.require(algorithm).build();	
		DecodedJWT decodeJWT = verifier.verify(refreshToken);
		String userName = decodeJWT.getSubject();
		List<String> roles = decodeJWT.getClaim("roles").asList(String.class);
		return createAccessToken(userName, roles);
	}
	
	private String getAccessToken(String userName, List<String> roles, Date now, Date vality) {
		String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.build().toUriString();
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(vality)
				.withSubject(userName)
				.withIssuer(issuerUrl)
				.sign(algorithm)
				.strip();
	}
	
private String getRefreshToken(String userName, List<String> roles, Date now) {
		Date valityRefreshToken = new Date(now.getTime() + validityInMilliseconds * 3);
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(valityRefreshToken)
				.withSubject(userName)
				.sign(algorithm)
				.strip();
	}

public Authentication getAuthentication (String token) {
	DecodedJWT decodeJWT = decodedToken(token);
	UserDetails userDetails = this.userService
			.loadUserByUsername(decodeJWT.getSubject());
	return new UsernamePasswordAuthenticationToken(userDetails, ""
			, userDetails.getAuthorities());
}

private DecodedJWT decodedToken(String token) {
	Algorithm  alg = Algorithm.HMAC256(secretKey.getBytes());
	JWTVerifier verifier = JWT.require(alg).build();
	DecodedJWT decodeJWT =verifier.verify(token);
	return decodeJWT;
}

public String resolveToken (HttpServletRequest req) {
	String bearerToken = req.getHeader("Authorization");
	if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
		return bearerToken.substring("Bearer ".length());
	}	
	return null;
	}

public boolean validateToken(String token) {
	DecodedJWT decodeJWT = decodedToken(token);
	try {
		if(decodeJWT.getExpiresAt().before(new Date())) {
			return false;
		}
		return true;
	} catch (Exception e) {
		throw new InvalidJwtAuthenticationException("Expired or invalid JWT Token!");
	}
	
}

}
