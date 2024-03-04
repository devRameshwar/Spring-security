package com.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.jwtToken.TokenHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenFilter extends OncePerRequestFilter {

	Logger logger = LoggerFactory.getLogger(TokenFilter.class);

	@Autowired
	private TokenHelper tokenHelper;

	@Autowired
	private UserAuthenticationServiceImp authenticationServiceImp;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		logger.info("Vailedation of JWT token By Once Per Request Filter");

		String token = request.getHeader("Authorization");
		logger.info("JWT Token " + token);

		String userName = null;

		if (token != null) {
			userName = this.tokenHelper.getUserNameByToken(token);
			logger.info("User Name By Token: " + userName);

		} else {
			logger.info("Token is Missing plz come with Token");
		}
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() != null) {

			// Fetch User Details By UserName
			UserDetails details = this.authenticationServiceImp.loadUserByUsername(userName);
			Boolean validationToken = this.tokenHelper.validationToken(token, details.getUsername());

			if (validationToken) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						details, null, details.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}

		}

		filterChain.doFilter(request, response);

	}

}
