package com.jwtToken;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHelper {

	public static final long  JWT_TOKEN_VAILEDITY_MILLIS = 5 * 60000;

	private String secret = "asdfghjksdfghjkertyuQWERTYZXCVBNASDFGPOIUpoiuytmnbv";

	// retrieve userName from Token
	public String getUserNameByToken(String token) {

		return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
	}

	// Check Token has expired
	public boolean isTokenExpired(String token) {

		final Date date = Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getExpiration();
		return date.before(new Date());
	}

	// Generate Token for User

	// SignIn the JWT using the HS512 algorithm and secret key.
	public String generateToken(String userNmae) {

		return Jwts.builder().setSubject(userNmae).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VAILEDITY_MILLIS))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	// Validate Token
	public Boolean validationToken(String token, String userName) {
		final String username = getUserNameByToken(token);
		return (username.equals(userName)) && !isTokenExpired(token);

	}

}
